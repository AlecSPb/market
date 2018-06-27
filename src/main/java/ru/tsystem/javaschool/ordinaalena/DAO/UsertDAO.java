package ru.tsystem.javaschool.ordinaalena.DAO;

import ru.tsystem.javaschool.ordinaalena.models.UsertEntity;

public interface UsertDAO {

    UsertEntity create(String username, String password);

    UsertEntity getById(int id);

    void delete(int id);

    UsertEntity update(int id,String username, String password);
}

