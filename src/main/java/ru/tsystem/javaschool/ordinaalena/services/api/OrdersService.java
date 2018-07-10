package ru.tsystem.javaschool.ordinaalena.services.api;


import ru.tsystem.javaschool.ordinaalena.DTO.CustomerDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.OrdersDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;

import java.util.List;

public interface OrdersService {
    /**
     * Create new order for user.
     *
     * @param ordersDTO  Order.
     */
    public void makeNewOrder(OrdersDTO ordersDTO);

    /**
     * Get all user's orders.
     *
     * @param email User's email.
     * @return List with orders.
     */
    public List<OrdersDTO> getCustomerOrders(String email);

    public List<OrdersDTO> getAll();

    public OrdersDTO getOrder(int orderId);

    /**
     * Return statistic about sold products
     * @return
     */
    public List<ProductDTO> getAllBuyingCounts();


    public List<CustomerDTO>  getAllCustomersCounts();

}
