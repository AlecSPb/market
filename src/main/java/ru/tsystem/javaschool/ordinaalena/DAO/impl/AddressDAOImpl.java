package ru.tsystem.javaschool.ordinaalena.DAO.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.tsystem.javaschool.ordinaalena.DAO.api.AddressDAO;
import ru.tsystem.javaschool.ordinaalena.SessionFactorySingleton;
import ru.tsystem.javaschool.ordinaalena.models.Address;

public class AddressDAOImpl implements AddressDAO {
    private final SessionFactory sessionFactory;
    private static AddressDAOImpl instance;

    public AddressDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public static AddressDAO getAddressDAOInstance(){
        if (instance == null){
            synchronized (AddressDAOImpl.class){
                instance = new AddressDAOImpl(SessionFactorySingleton.getSessionFactoryInstance());
            }
        }
        return instance;
    }
    @Override
    public Address create(String postcode, String country, String city, String street, String building, String apartment) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Address address = new Address ();
        address.setPostcode(postcode);
        address.setCountry(country);
        address.setCity(city);
        address.setStreet(street);
        address.setBuilding(building);
        address.setApartment(apartment);
        session.persist(address);
        transaction.commit();
        if (session.isOpen()) session.close();
        return address;
    }

    @Override
    public Address getById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Address address= session.get(Address.class, id);
        transaction.commit();
        if (session.isOpen()) session.close();
        return address;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Address address = getById(id);
        session.delete(address);
        transaction.commit();
        if (session.isOpen()) session.close();
    }

    @Override
    public Address update(int id, String postcode, String country, String city, String street, String building, String apartment) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Address address = session.get(Address.class, id);
        address.setPostcode(postcode);
        address.setCountry(country);
        address.setCity(city);
        address.setStreet(street);
        address.setBuilding(building);
        address.setApartment(apartment);
        session.saveOrUpdate(address);
        transaction.commit();
        if (session.isOpen()) session.close();
        return address;
    }
}
