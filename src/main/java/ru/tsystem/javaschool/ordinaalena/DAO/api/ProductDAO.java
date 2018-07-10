
package ru.tsystem.javaschool.ordinaalena.DAO.api;

import ru.tsystem.javaschool.ordinaalena.entities.Product;

import java.util.List;

public interface ProductDAO {
    void persist(Product product);
    Product find(int id, Class<Product> className);
    void remove(Product product);
    void merge(   Product product);
    List<Product> getAll(Class<Product> className);
    public List<Product> getByCategory(String category);

    public List<String> getCategories();

    public Product getByTitle(String title);

    public List<Product> getByCategories(String[] categories);

    public long getProductsCount(String[] categories);

    public long getProductsCount();
}