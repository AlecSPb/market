package ru.tsystem.javaschool.ordinaalena.DAO.api;

import ru.tsystem.javaschool.ordinaalena.entities.Customer;

import java.util.List;

public interface CustomerDAO {
    void persist(Customer customer);

    Customer find(int id, Class<Customer> className);

    void remove(final Customer model);

    void merge(Customer customer);
    public List<Customer> getAll(Class<Customer> className);

    public Customer getByEmail(String email);


    public int getCustomerIdByEmail(String email);
}