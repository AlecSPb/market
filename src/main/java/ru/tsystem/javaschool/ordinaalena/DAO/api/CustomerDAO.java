package ru.tsystem.javaschool.ordinaalena.DAO.api;

import ru.tsystem.javaschool.ordinaalena.entities.Customer;

import java.util.List;

public interface CustomerDAO {
    void persist(Customer customer);

    Customer find(int id, Class<Customer> className);

    void remove(final Customer model);

    void merge(Customer customer);

    List<Customer> getAll(Class<Customer> className);

    Customer getByEmail(String email);

    int getCustomerIdByEmail(String email);
}