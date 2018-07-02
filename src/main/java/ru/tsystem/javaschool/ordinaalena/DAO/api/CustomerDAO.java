package ru.tsystem.javaschool.ordinaalena.DAO.api;

import ru.tsystem.javaschool.ordinaalena.models.Customer;

import java.sql.Date;

public interface CustomerDAO {
    Customer create( String firstname,String secondname,Date dob,String eMail,String parole, String phonenumber);

    Customer getById(int id);

    void delete(int id);

    Customer update(int id,String firstname,String secondname,Date dob,String eMail,String parole, String phonenumber);
}
