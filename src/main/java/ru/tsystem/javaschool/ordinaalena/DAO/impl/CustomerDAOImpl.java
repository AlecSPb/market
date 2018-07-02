package ru.tsystem.javaschool.ordinaalena.DAO.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.tsystem.javaschool.ordinaalena.DAO.api.CustomerDAO;
import ru.tsystem.javaschool.ordinaalena.SessionFactorySingleton;
import ru.tsystem.javaschool.ordinaalena.models.Customer;

import java.sql.Date;

public class CustomerDAOImpl  implements CustomerDAO {
    private final SessionFactory sessionFactory;
    private static CustomerDAOImpl instance;

    public CustomerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public static CustomerDAO getUsertDAOInstance(){
        if (instance == null){
            synchronized (CustomerDAOImpl.class){
                instance = new CustomerDAOImpl(SessionFactorySingleton.getSessionFactoryInstance());
            }
        }
        return instance;
    }

    @Override
    public Customer create(String firstname, String secondname, Date dob, String eMail, String parole, String phonenumber) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer ();
        customer.setFirstname(firstname);
        customer.setSecondname(secondname);
        customer.setDob(dob);
        customer.seteMail(eMail);
        customer.setParole(parole);
        customer.setPhonenumber(phonenumber);
        session.persist(customer);
        transaction.commit();
        if (session.isOpen()) session.close();
        return customer;
    }

    @Override
    public Customer getById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        transaction.commit();
        if (session.isOpen()) session.close();
        return customer;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
         Customer customer = getById(id);
        session.delete(customer);
        transaction.commit();
        if (session.isOpen()) session.close();
    }

    @Override
    public Customer update(int id, String firstname, String secondname, Date dob, String eMail, String parole, String phonenumber) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
       customer.setFirstname(firstname);
       customer.setSecondname(secondname);
       customer.setDob(dob);
       customer.seteMail(eMail);
       customer.setParole(parole);
       customer.setPhonenumber(phonenumber);
        session.saveOrUpdate(customer);
        transaction.commit();
        if (session.isOpen()) session.close();
        return customer;
    }
}
