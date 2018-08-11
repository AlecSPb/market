package ru.tsystem.javaschool.ordinaalena.services.impl;

import ru.tsystem.javaschool.ordinaalena.constants.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystem.javaschool.ordinaalena.DAO.api.CustomerDAO;
import ru.tsystem.javaschool.ordinaalena.DAO.api.OrdersDAO;
import ru.tsystem.javaschool.ordinaalena.DAO.api.ProductDAO;
import ru.tsystem.javaschool.ordinaalena.DTO.OrdersDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;
import ru.tsystem.javaschool.ordinaalena.converter.Converter;
import ru.tsystem.javaschool.ordinaalena.entities.Customer;
import ru.tsystem.javaschool.ordinaalena.entities.Orders;
import ru.tsystem.javaschool.ordinaalena.entities.Product;
import ru.tsystem.javaschool.ordinaalena.services.api.CartService;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Set;

@Service
public class CartServiceImpl  implements CartService {

    private CustomerDAO customerDAO;

    private OrdersDAO ordersDAO;

    private ProductDAO productDAO;

    private Converter converter;
    @Autowired
    public CartServiceImpl(CustomerDAO customerDAO, OrdersDAO ordersDAO, ProductDAO productDAO, Converter converter) {
        this.customerDAO = customerDAO;
        this.ordersDAO = ordersDAO;
        this.productDAO = productDAO;
        this.converter = converter;
    }

    @Override
    @Transactional
    public void deleteFromCart(String email, String productTitle) {
        Set<Product> products = this.getCart().getProducts();
        products.remove(productDAO.getByTitle(productTitle));
    }

    @Override
    @Transactional
    public void deleteFromCart( List<ProductDTO> bagProducts,Integer[]productId) {
        for ( Integer id:productId)
     bagProducts.remove(converter.convertToDTO(productDAO.getById(id)));

    }

    @Override
    @Transactional
    public void deleteFromCart(String email, List<ProductDTO> removeProducts) {
        Set<Product> products = this.getCart().getProducts();
        for(ProductDTO productDTO:removeProducts)
            products.remove(productDAO.find(productDTO.getId(), Product.class));
    }
    @Override
    @Transactional
    public void deleteFromCart( List<ProductDTO> bagProducts,int productId) {
        bagProducts.remove(converter.convertToDTO(productDAO.getById(productId)));
    }

    @Override
    @Transactional
    public void deleteFromCart(OrdersDTO ordersDTO) {
        this.deleteFromCart(ordersDTO.getCustomerEmail(), ordersDTO.getProductDTOs());
    }

    @Override
    @Transactional
    public void addToCart( int productId, List<ProductDTO> bagProducts) {
        bagProducts.add(converter.convertToDTO(productDAO.find(productId, Product.class)));

    }

    @Override
    @Transactional
    public OrdersDTO getCustomerCart() {
        Orders customerCart = getCart();
        return converter.convertToDTO(customerCart);
    }

    private Orders getCart(){
        Orders cart;
            cart = createCustomerCart();

        return cart;
    }

    private Orders createCustomerCart(){
        Orders customerCart = new Orders();
        customerCart.setOrderStatus(OrderStatus.BUCKET);
        ordersDAO.persist(customerCart);
        return customerCart;
    }
}
