package ru.tsystem.javaschool.ordinaalena.DAO.api;

import ru.tsystem.javaschool.ordinaalena.entities.ProductParameter;

import java.util.List;

public interface ProductParameterDAO {
    void persist(ProductParameter productParameter);
    ProductParameter find(int id, Class<ProductParameter> className);
    void remove(final ProductParameter productParameter);
    void merge(ProductParameter productParameter);
    List<ProductParameter> getAll(Class<ProductParameter> className);
}