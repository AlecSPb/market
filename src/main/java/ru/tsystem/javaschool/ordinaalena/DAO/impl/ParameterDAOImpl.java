package ru.tsystem.javaschool.ordinaalena.DAO.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.tsystem.javaschool.ordinaalena.DAO.api.ParameterDAO;
import ru.tsystem.javaschool.ordinaalena.models.Parameter;

public class ParameterDAOImpl implements ParameterDAO {
    private SessionFactory sessionFactory;

    private ParameterDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Parameter create(String parameterName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Parameter parameter = new Parameter();
        parameter.setParameterName(parameterName);
        session.persist(parameter);
        transaction.commit();
        if (session.isOpen()) session.close();
        return parameter;


    }

    @Override
    public Parameter getById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Parameter parameter = session.get(Parameter.class, id);
        transaction.commit();
        if (session.isOpen()) session.close();
        return parameter;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Parameter parameter = getById(id);
        session.delete(parameter);
        transaction.commit();
        if (session.isOpen()) session.close();
    }

    @Override
    public Parameter update(int id,int categoryId, String parameterName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Parameter parameter = session.get(Parameter.class, id);
        parameter.setParameterName(parameterName);
        session.saveOrUpdate(parameter);
        transaction.commit();
        if (session.isOpen()) session.close();
        return parameter;
    }
}