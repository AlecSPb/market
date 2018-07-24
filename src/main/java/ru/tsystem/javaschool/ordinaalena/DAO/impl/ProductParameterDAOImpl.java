
package ru.tsystem.javaschool.ordinaalena.DAO.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.tsystem.javaschool.ordinaalena.DAO.api.ProductParameterDAO;
import ru.tsystem.javaschool.ordinaalena.entities.ProductParameter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductParameterDAOImpl  implements ProductParameterDAO {
    private static final Logger logger=Logger.getLogger(ProductParameterDAOImpl.class);
    @PersistenceContext
     private EntityManager entityManager;


    @Override
    public void persist(ProductParameter productParameter) {
        logger.info("persist new " + productParameter.getClass());

        entityManager.persist(productParameter);
    }

    @Override
    public ProductParameter find(int id, Class<ProductParameter> className) {
        logger.info("find by id " + className + " id " + id);
        return entityManager.find(className, id);
    }

    @Override
    public void remove(ProductParameter productParameter) {
        logger.info("remove " + productParameter.getClass() + " id " + productParameter.getId());
        entityManager.remove(entityManager.merge(productParameter));
    }

    @Override
    public void merge(ProductParameter productParameter) {
        logger.info("merge " + productParameter.getClass() + " id " + productParameter.getId());
        entityManager.merge(productParameter);
    }

    @Override
    public List<ProductParameter> getAll(Class<ProductParameter> className) {
        logger.info("find all " + className);
        return entityManager.
                createQuery("from "+className.getSimpleName(), className).
                getResultList();
    }
}