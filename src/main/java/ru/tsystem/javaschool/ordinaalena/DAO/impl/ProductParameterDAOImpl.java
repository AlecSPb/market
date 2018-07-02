package ru.tsystem.javaschool.ordinaalena.DAO.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.tsystem.javaschool.ordinaalena.DAO.api.ProductParameterDAO;
import ru.tsystem.javaschool.ordinaalena.models.ProductParameter;

public class ProductParameterDAOImpl  implements ProductParameterDAO {
    private SessionFactory sessionFactory;
    private ProductParameterDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }
    @Override
    public ProductParameter create(int parameterId, String parameterValue) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProductParameter productParameter = new ProductParameter();
        productParameter.setParameterId(parameterId);
        productParameter.setParameterValue(parameterValue);
        session.persist(productParameter);
        transaction.commit();
        if (session.isOpen()) session.close();
        return productParameter;
    }

    @Override
    public ProductParameter getById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProductParameter productParameter = session.get(ProductParameter.class, id);
        transaction.commit();
        if (session.isOpen()) session.close();
        return productParameter;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProductParameter productParameter = getById(id);
        session.delete(productParameter);
        transaction.commit();
        if (session.isOpen()) session.close();
    }

    @Override
    public ProductParameter update(int id, int parameterId, String parameterValue) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProductParameter productParameter = new ProductParameter();
        productParameter.setParameterId(parameterId);
        productParameter.setParameterValue(parameterValue);
        session.saveOrUpdate(productParameter);
        transaction.commit();
        if (session.isOpen()) session.close();
        return productParameter;
    }
}
