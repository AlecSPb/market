package ru.tsystem.javaschool.ordinaalena.DAO.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.tsystem.javaschool.ordinaalena.DAO.api.ProductDAO;
import ru.tsystem.javaschool.ordinaalena.models.Product;

public class ProductDAOImpl implements ProductDAO {
    private SessionFactory sessionFactory;
    private ProductDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }
    @Override
    public Product create(String title, String price, String brand, Integer count) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setBrand(brand);
        product.setCount(count);
        session.persist(product);
        transaction.commit();
        if (session.isOpen()) session.close();
        return product;
    }

    @Override
    public Product getById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.get(Product.class, id);
        transaction.commit();
        if (session.isOpen()) session.close();
        return product;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
       Product product = getById(id);
        session.delete(product);
        transaction.commit();
        if (session.isOpen()) session.close();
    }

    @Override
    public Product update(int id, int categoryId, String title, String price, String brand, Integer count) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.get(Product.class, id);
        product.setTitle(title);
        product.setPrice(price);
        product.setBrand(brand);
        product.setCount(count);
        session.saveOrUpdate(product);
        transaction.commit();
        if (session.isOpen()) session.close();
        return product;
    }


}
