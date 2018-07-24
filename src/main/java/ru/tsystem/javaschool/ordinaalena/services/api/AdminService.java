package ru.tsystem.javaschool.ordinaalena.services.api;

import ru.tsystem.javaschool.ordinaalena.constants.OrderStatus;
import ru.tsystem.javaschool.ordinaalena.DTO.CustomerDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;

import java.util.List;

public interface AdminService {
    /**
     * Get all customers.
     * @return Customer's dtos
     */
     List<CustomerDTO> getCustomers();

    /**
     * Get all products.
     * @return Products's dtos
     */
     List<ProductDTO> getProducts();

    /**
     * Change order status.
     * @param ordersId   OrderId.
     * @param status    New order status.
     */
     void changeOrdersStatus(int ordersId, OrderStatus status);

    /**
     * Change order status.
     * @param ordersId   OrderId.
     * @return          New order status.
     */
    String changeOrderStatus(int ordersId);

    /**
     * Change product status.
     * @param productId   ProductID.
     * @return          New product status.
     */
    String changeProductStatus(int productId);

}
