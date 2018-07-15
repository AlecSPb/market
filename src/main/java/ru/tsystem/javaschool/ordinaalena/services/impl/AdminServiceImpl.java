package ru.tsystem.javaschool.ordinaalena.services.impl;

import constants.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystem.javaschool.ordinaalena.DAO.api.CustomerDAO;
import ru.tsystem.javaschool.ordinaalena.DAO.api.OrdersDAO;
import ru.tsystem.javaschool.ordinaalena.DAO.api.ProductDAO;
import ru.tsystem.javaschool.ordinaalena.DTO.CustomerDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;
import ru.tsystem.javaschool.ordinaalena.converter.Converter;
import ru.tsystem.javaschool.ordinaalena.entities.Customer;
import ru.tsystem.javaschool.ordinaalena.entities.Orders;
import ru.tsystem.javaschool.ordinaalena.entities.Product;
import ru.tsystem.javaschool.ordinaalena.services.api.AdminService;
import ru.tsystem.javaschool.ordinaalena.services.api.OrdersService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private CustomerDAO customerDAO;

    private OrdersDAO ordersDAO;

    private ProductDAO productDAO;

    private OrdersService ordersService;

    private Converter converter;

    @Autowired
 public AdminServiceImpl(CustomerDAO customerDAO, OrdersDAO ordersDAO, ProductDAO productDAO,OrdersService ordersService, Converter converter){
     this.customerDAO=customerDAO;
     this.ordersDAO=ordersDAO;
     this.productDAO=productDAO;
     this.ordersService=ordersService;
     this.converter=converter;
 }

    @Override
    @Transactional
    public List<CustomerDTO> getCustomers() {
        return customerDAO.getAll(Customer.class).
                stream().map(customer -> converter.convertToDTO(customer))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProducts() {
        return productDAO.getAll(Product.class)
                .stream().map(product -> converter.convertToDTO(product))
                .collect(Collectors.toList());
    }

    @Override
    public void changeOrdersStatus(int ordersId, OrderStatus status) {
        Orders orders = ordersDAO.find(ordersId, Orders.class);
        orders.setOrderStatus(status);
        ordersDAO.merge(orders);
    }

    @Override
    public String changeOrderStatus(int ordersId) {
        Orders orders = ordersDAO.find(ordersId, Orders.class);

        OrderStatus status = orders.getOrderStatus();
        if(status.equals(OrderStatus.UNDONE))
            orders.setOrderStatus(OrderStatus.DONE);
        else if(status.equals(OrderStatus.DONE))
            orders.setOrderStatus(OrderStatus.UNDONE);

        ordersDAO.merge(orders);
        return orders.getOrderStatus().toString();
    }


    @Override
    public String changeProductStatus(int productId) {
        Product product = productDAO.find(productId, Product.class);
        product.setNotavailable(!product.isNotavailable());
        productDAO.merge(product);
        return String.valueOf(product.isNotavailable());
    }
}
