package ru.tsystem.javaschool.ordinaalena.DAO.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.tsystem.javaschool.ordinaalena.DAO.api.OrdersDAO;
import ru.tsystem.javaschool.ordinaalena.models.Orders;

public class OrdersDAOImpl implements OrdersDAO {
    private final SessionFactory sessionFactory;

    public OrdersDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Orders create(String paymentMethod, Integer addressId, String ordersStatus) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Orders order = new Orders ();
        order.setPaymentMethod(paymentMethod);
        order.setAddressId(addressId);
        order.setOrdersStatus(ordersStatus);
        session.persist(order);
        transaction.commit();
        if (session.isOpen()) session.close();
        return order;

    }

    @Override
    public Orders getById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Orders order = session.get(Orders.class, id);
        transaction.commit();
        if (session.isOpen()) session.close();
        return order;
    }

    @Override
    public void delete(int id) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Orders order = getById(id);
            session.delete(order);
            transaction.commit();
            if (session.isOpen()) session.close();
    }

    @Override
    public Orders update(int id, int customerId, String paymentMethod, Integer addressId, String ordersStatus) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Orders order = session.get(Orders.class, id);
       order.setCustomerId(customerId);
       order.setPaymentMethod(paymentMethod);
       order.setAddressId(addressId);
       order.setOrdersStatus(ordersStatus);
        session.saveOrUpdate(order);
        transaction.commit();
        if (session.isOpen()) session.close();
        return order;
    }
}
