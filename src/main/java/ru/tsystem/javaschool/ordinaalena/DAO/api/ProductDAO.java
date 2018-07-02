package ru.tsystem.javaschool.ordinaalena.DAO.api;

import ru.tsystem.javaschool.ordinaalena.models.Product;

public interface ProductDAO {
    Product create( String title,String price, String brand, Integer count);

    Product getById(int id);

    void delete(int id);

    Product update(int id,int categoryId, String title,String price, String brand, Integer count);
}
