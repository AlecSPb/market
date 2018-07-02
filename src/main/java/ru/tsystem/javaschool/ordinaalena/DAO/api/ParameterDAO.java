package ru.tsystem.javaschool.ordinaalena.DAO.api;

import ru.tsystem.javaschool.ordinaalena.models.Parameter;

public interface ParameterDAO {
    Parameter create(String parameterName);

    Parameter getById(int id);

    void delete(int id);

   Parameter update(int id,int categoryId,String parameterName);
}
