package ru.tsystem.javaschool.ordinaalena.DAO.api;

import ru.tsystem.javaschool.ordinaalena.models.Address;



public interface AddressDAO {
    Address create(String postcode, String country, String city,String street, String building, String apartment);

    Address getById(int id);

    void delete(int id);

    Address update(int id,String postcode, String country, String city,String street, String building, String apartment);
}
