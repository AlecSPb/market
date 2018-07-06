
package ru.tsystem.javaschool.ordinaalena.DAO.api;

import ru.tsystem.javaschool.ordinaalena.entities.Address;

import java.util.List;


public interface AddressDAO {
    void persist(Address object);


    Address find(int id, Class<Address> className);


    void remove(final Address model);


    void merge(Address address);


    List<Address> getAll(Class<Address> className);
    List<Address> getByClientId(int id);
}
