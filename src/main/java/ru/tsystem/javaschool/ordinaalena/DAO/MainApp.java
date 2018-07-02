package ru.tsystem.javaschool.ordinaalena.DAO;


import org.hibernate.SessionFactory;
import ru.tsystem.javaschool.ordinaalena.DAO.api.AddressDAO;
import ru.tsystem.javaschool.ordinaalena.DAO.impl.AddressDAOImpl;


public class MainApp {
    public static void main(String[] args){
        SessionFactory sessionFactory;

        AddressDAO addressDAO=AddressDAOImpl.getAddressDAOInstance();
        addressDAO.create("ffff","ffghjjj","ggg","gghfghf","hb","h");
        System.out.println(addressDAO.getById(1));
    }
}
