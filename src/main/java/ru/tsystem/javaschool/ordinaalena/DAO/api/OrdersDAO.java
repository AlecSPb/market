package ru.tsystem.javaschool.ordinaalena.DAO.api;

import ru.tsystem.javaschool.ordinaalena.models.Orders;


public interface OrdersDAO {
    Orders create( String paymentMethod,Integer addressId,String ordersStatus);

    Orders getById(int id);

    void delete(int id);

    Orders update(int id,int customerId,String paymentMethod,Integer addressId,String ordersStatus);
}
