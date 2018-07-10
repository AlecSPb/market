package ru.tsystem.javaschool.ordinaalena.services.impl;

import constants.OrderStatus;
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
    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    OrdersDAO ordersDAO;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    Converter converter;

    @Override
    @Transactional
    public void deleteFromCart(String email, String productTitle) {
        Set<Product> products = this.getCart(email).getProducts();
        products.remove(productDAO.getByTitle(productTitle));
    }

    @Override
    @Transactional
    public void deleteFromCart(String email, String[] productTitles) {
        Set<Product> products = this.getCart(email).getProducts();
        for(String title:productTitles)
            products.remove(productDAO.getByTitle(title));
    }

    @Override

    public void deleteFromCart(String email, List<ProductDTO> removeProducts) {
        Set<Product> products = this.getCart(email).getProducts();
        for(ProductDTO productDTO:removeProducts)
            products.remove(productDAO.find(productDTO.getId(), Product.class));
    }

    @Override
    @Transactional
    public void deleteFromCart(OrdersDTO ordersDTO) {
        this.deleteFromCart(ordersDTO.getCustomerEmail(), ordersDTO.getProductDTOs());
    }

    @Override
    @Transactional
    public void addToCart(String email, int productId) {
        Set<Product> products = this.getCart(email).getProducts();
        products.add(productDAO.find(productId, Product.class));
    }

    @Override
    @Transactional
    public OrdersDTO getCustomerCart(String email) {
        Orders customerCart = getCart(email);
        return converter.convertToDTO(customerCart);
    }
    private Orders getCart(String email){
        int customerId = customerDAO.getCustomerIdByEmail(email);
        Orders cart;
        try {
            cart = ordersDAO.getClientBucket(customerId);
        } catch (NoResultException exc){
            cart = createCustomerCart(email);
        }
        return cart;
    }

    private Orders createCustomerCart(String email){
        Orders customerCart = new Orders();
        customerCart.setOrderStatus(OrderStatus.BUCKET);
        customerCart.setCustomer(
                customerDAO.find(customerDAO.getCustomerIdByEmail(email), Customer.class));
        ordersDAO.persist(customerCart);
        return customerCart;
    }
}
