package ru.tsystem.javaschool.ordinaalena.DAO.api;

import ru.tsystem.javaschool.ordinaalena.entities.Orders;

import java.util.List;

public interface OrdersDAO {
    void persist(Orders orders);
    Orders find(int id, Class<Orders> className);
    void remove(final Orders orders);
    void merge(Orders orders);
    List<Orders> getAll(Class<Orders> className);
    Orders getClientBucket(int clientId);
    List<Orders> getClientOrders(int clientId);
    List<Orders> ordersWithAddresses(int addressId);
    long getProductCounts(int productId);
    long getUserBuyingCounts(int customerId);
    List<Orders> findOrdersByCustomerId(int customerId);
}