package ru.tsystem.javaschool.ordinaalena.DAO;

import ru.tsystem.javaschool.ordinaalena.models.UsertEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class UsertDAOImpl implements UsertDAO{

    private final SessionFactory sessionFactory;

    public UsertDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public UsertEntity create(String username, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UsertEntity u = new UsertEntity (username, password);
        session.persist(u);
        transaction.commit();
        if (session.isOpen()) session.close();
        return u;
    }

    @Override
    public UsertEntity getById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UsertEntity u = session.get(UsertEntity.class, id);
        transaction.commit();
        if (session.isOpen()) session.close();
        return u;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UsertEntity u = getById(id);
        session.delete(u);
        transaction.commit();
        if (session.isOpen()) session.close();

    }

    @Override
    public void update(int id) {
        // ...
    }
}

