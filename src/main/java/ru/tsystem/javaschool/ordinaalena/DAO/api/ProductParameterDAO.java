package ru.tsystem.javaschool.ordinaalena.DAO.api;

import ru.tsystem.javaschool.ordinaalena.models.ProductParameter;

public interface ProductParameterDAO {
    ProductParameter create( int parameterId, String parameterValue);

    ProductParameter getById(int id);

    void delete(int id);

    ProductParameter update(int id,int parameterId, String parameterValue);
}
