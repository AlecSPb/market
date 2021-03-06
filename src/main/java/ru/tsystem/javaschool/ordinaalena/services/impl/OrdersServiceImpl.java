package ru.tsystem.javaschool.ordinaalena.services.impl;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import ru.tsystem.javaschool.ordinaalena.DTO.AddressDTO;
import ru.tsystem.javaschool.ordinaalena.constants.OrderStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystem.javaschool.ordinaalena.DAO.api.AddressDAO;
import ru.tsystem.javaschool.ordinaalena.DAO.api.CustomerDAO;
import ru.tsystem.javaschool.ordinaalena.DAO.api.OrdersDAO;
import ru.tsystem.javaschool.ordinaalena.DAO.api.ProductDAO;
import ru.tsystem.javaschool.ordinaalena.DTO.CustomerDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.OrdersDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;
import ru.tsystem.javaschool.ordinaalena.converter.Converter;
import ru.tsystem.javaschool.ordinaalena.entities.Address;
import ru.tsystem.javaschool.ordinaalena.entities.Customer;
import ru.tsystem.javaschool.ordinaalena.entities.Orders;
import ru.tsystem.javaschool.ordinaalena.entities.Product;
import ru.tsystem.javaschool.ordinaalena.services.api.CartService;
import ru.tsystem.javaschool.ordinaalena.services.api.OrdersService;
import ru.tsystem.javaschool.ordinaalena.services.api.ProductOrdersService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class OrdersServiceImpl implements OrdersService {

    private CustomerDAO customerDAO;

    private OrdersDAO ordersDAO;

    private ProductDAO productDAO;

    private AddressDAO addressDAO;

    private CartService cartService;

    private ProductOrdersService productOrdersService;
    @Autowired
    private MailSender mailSender;

    @Autowired
    public OrdersServiceImpl(CustomerDAO customerDAO, OrdersDAO ordersDAO, ProductDAO productDAO, AddressDAO addressDAO, CartService cartService, ProductOrdersService productOrdersService, Converter converter) {
        this.customerDAO = customerDAO;
        this.ordersDAO = ordersDAO;
        this.productDAO = productDAO;
        this.addressDAO = addressDAO;
        this.cartService = cartService;
        this.productOrdersService = productOrdersService;
        this.converter = converter;
    }

    private Converter converter;


    private static final Logger logger = Logger.getLogger(OrdersServiceImpl.class);


    @Override
    @Transactional
    public void makeNewOrder(OrdersDTO ordersDTO) {
        logger.info("create new");

        ordersDTO.setOrderStatus(OrderStatus.PENDING.toString());
        Orders order = converter.convertToEntity(ordersDTO);
        order.setProducts(getProductSet(ordersDTO));
        order.setCustomer(customerDAO.getByEmail(ordersDTO.getCustomerEmail()));
        order.setAddress(addressDAO.find(ordersDTO.getAddress().getId(), Address.class));
        ordersDAO.persist(order);
        ordersDTO.setId(order.getId());
        cartService.deleteFromCart(ordersDTO);
         setCounts(ordersDTO);
         decriminateProducts(ordersDTO);}

        private void setCounts(OrdersDTO dto) {
    for (int i = 0; i < dto.getCounts().size(); i++) {
        productOrdersService.setCount(dto.getProductDTOs().get(i).getId(),
                dto.getId(), Integer.valueOf(dto.getCounts().get(i)));
    }

}
    private void decriminateProducts(OrdersDTO dto) {
        for (int i=0; i<dto.getCounts().size(); i++){
            Product product = productDAO.getById(dto.getProductDTOs().get(i).getId());
            product.setCount(product.getCount()-Integer.valueOf(dto.getCounts().get(i)));
            if(product.getCount()==0) product.setNotavailable(true);
            productDAO.merge(product);
        }
    }
    private Set<Product> getProductSet(OrdersDTO ordersDTO){
        Set<Product> products = new HashSet<>();
        for(ProductDTO product : ordersDTO.getProductDTOs())
            products.add(productDAO.getById(product.getId()));
        return products;
    }
    @Override
    @Transactional
    public List<OrdersDTO> getCustomerOrders(String email) {
        List<Orders> orders =
               ordersDAO.getClientOrders(customerDAO.getCustomerIdByEmail(email));
        List<OrdersDTO> ordersDTOS =  orders.stream().map(order -> converter.convertToDTO(order))
                .collect(Collectors.toList());

        for(OrdersDTO orderDTO:ordersDTOS)
            orderDTO.setCounts(getCounts(orderDTO));

        return ordersDTOS;
    }
    private List<String> getCounts(OrdersDTO ordersDTO){
        List<String> counts = new ArrayList<>();
        for(ProductDTO product : ordersDTO.getProductDTOs()){
            counts.add(String.valueOf(
                   productOrdersService.getCount(product.getId(), ordersDTO.getId())));
        }
        return counts;
    }

    @Override
    @Transactional
    public List<OrdersDTO> getAll() {
        List<Orders> orders =
                ordersDAO.getAll(Orders.class);
        List<OrdersDTO> ordersDTOS =  orders.stream().map(order -> converter.convertToDTO(order))
                .collect(Collectors.toList());

        for(OrdersDTO orderDto:ordersDTOS)
            orderDto.setCounts(getCounts(orderDto));

        return ordersDTOS;
    }

    @Override
    @Transactional
    public OrdersDTO getOrder(int orderId) {
        Orders orders = ordersDAO.find(orderId, Orders.class);
        OrdersDTO ordersDTO =  converter.convertToDTO(orders);

        ordersDTO.setCounts(getCounts(ordersDTO));

        return ordersDTO;
    }

    @Override
    @Transactional
    public List<ProductDTO> getAllBuyingCounts() {
        List<ProductDTO> products = productDAO
                .getAll(Product.class)
                .stream().map(product -> converter.convertToDTO(product))
                .collect(Collectors.toList());

        Long count;

        for(ProductDTO product: products){
            count = ordersDAO.getProductCounts(product.getId());
            product.setCount(count.intValue());

    }


        return products;
    }

    @Override
    @Transactional
    public List<CustomerDTO> getAllCustomersCounts() {
        List<CustomerDTO> customerDTOS= customerDAO.getAll(Customer.class)
                .stream().map(customer-> converter.convertToDTO(customer))
                .collect(Collectors.toList());

        Long count;

        for(CustomerDTO customer: customerDTOS){
            count = ordersDAO.getProductCounts(customer.getId());
            customer.setPhonenumber(count.toString());
    }

        return customerDTOS;
    }


    @Override
    @Transactional
    public List <CustomerDTO> findTopCustomer(){
        List <CustomerDTO> customerDTOs=customerDAO.getTopCustomers()
                .stream().map(customer -> converter.convertToDTO(customer))
                .collect(Collectors.toList());
        return customerDTOs;
    }
    @Override
    public void sendMessage(OrdersDTO orders, CustomerDTO customer, List<ProductDTO> cart,
                            AddressDTO address, String source, String target, String title) {
        SimpleMailMessage msg = new SimpleMailMessage();

        StringBuilder products = new StringBuilder();
        int counter = 0;
        for (ProductDTO productDto : cart) {
            products.append(++counter).append(") ")
                    .append(productDto.getTitle()).append(" - ")
                    .append(productOrdersService.getCount(productDto.getId(),orders.getId())).append(" items.").append(" Price - $")
                    .append(productDto.getPrice()).append(".").append(System.lineSeparator());
        }

        String message = "Hi, " + customer.getFirstName() + "!" + System.lineSeparator()
                + "Your order[ID=" + orders.getId() + "] is confirmed." + System.lineSeparator()
                + "List of products: " + System.lineSeparator()
                + products.toString() + System.lineSeparator()
                + "Delivery address: " + address.toString() + System.lineSeparator() + System.lineSeparator()
                + "Thank you for choosing us!";

        msg.setFrom(source);
        msg.setTo(target);
        msg.setSubject(title);
        msg.setText(message);
        mailSender.send(msg);
    }
}
