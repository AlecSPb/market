package ru.tsystem.javaschool.ordinaalena.DAO.api;

import ru.tsystem.javaschool.ordinaalena.models.Category;

public interface CategoryDAO {
    Category create(String categoryName, Integer parentCategory);

    Category getById(int id);

    void delete(int id);

    Category update(int id,String categoryName, Integer parentCategory);
}

