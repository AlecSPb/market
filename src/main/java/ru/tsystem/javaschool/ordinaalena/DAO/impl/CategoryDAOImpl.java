package ru.tsystem.javaschool.ordinaalena.DAO.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.tsystem.javaschool.ordinaalena.DAO.api.CategoryDAO;
import ru.tsystem.javaschool.ordinaalena.models.Category;

public class CategoryDAOImpl  implements CategoryDAO {
    private final SessionFactory sessionFactory;

    public CategoryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Category create(String categoryName, Integer parentCategory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Category category = new Category ();
        category.setCategoryName(categoryName);
        category.setParentCategory(parentCategory);
        session.persist(category);
        transaction.commit();
        if (session.isOpen()) session.close();
        return category;
    }

    @Override
    public Category getById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Category category= session.get(Category.class, id);
        transaction.commit();
        if (session.isOpen()) session.close();
        return category;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Category category = getById(id);
        session.delete(category);
        transaction.commit();
        if (session.isOpen()) session.close();
    }

    @Override
    public Category update(int id, String categoryName, Integer parentCategory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Category category= session.get(Category.class, id);
        category.setCategoryName(categoryName);
       category.setParentCategory(parentCategory);
        session.saveOrUpdate(category);
        transaction.commit();
        if (session.isOpen()) session.close();
        return category;
    }
}
