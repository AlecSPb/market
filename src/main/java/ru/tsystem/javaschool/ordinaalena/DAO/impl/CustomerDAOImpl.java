package ru.tsystem.javaschool.ordinaalena.DAO.impl;

import ru.tsystem.javaschool.ordinaalena.DAO.api.CustomerDAO;
import ru.tsystem.javaschool.ordinaalena.models.Customer;

import java.sql.Date;

public class CustomerDAOImpl  implements CustomerDAO {
    @Override
    public Customer create(String firstname, String secondname, Date dob, String eMail, String parole, String phonenumber) {
        return null;
    }

    @Override
    public Customer getById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Customer update(int id, String firstname, String secondname, Date dob, String eMail, String parole, String phonenumber) {
        return null;
    }
}
