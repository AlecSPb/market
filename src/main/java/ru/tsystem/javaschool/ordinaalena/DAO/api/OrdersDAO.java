package ru.tsystem.javaschool.ordinaalena.DAO.api;

import ru.tsystem.javaschool.ordinaalena.entities.Orders;

import java.util.List;

public interface OrdersDAO {
    void persist(Orders orders);
    Orders find(int id, Class<Orders> className);
    void remove(final Orders orders);
    void merge(Orders orders);
    List<Orders> getAll(Class<Orders> className);
    public Orders getClientBucket(int clientId);
    public List<Orders> getClientOrders(int clientId);

    public List<Orders> ordersWithAddresses(int addressId);

    public long getProductCounts(int productId);

    public long getUserBuyingCounts(int customerId);
    public List<Orders> findOrdersByCustomerId(int customerId);
}