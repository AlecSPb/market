
package ru.tsystem.javaschool.ordinaalena.DAO.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.tsystem.javaschool.ordinaalena.DAO.api.RoleDAO;
import ru.tsystem.javaschool.ordinaalena.entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class  RoleDAOImpl implements RoleDAO {
    private static final Logger logger=Logger.getLogger(RoleDAOImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void persist(Role role) {
        logger.info("persist new " + role.getClass());

        entityManager.persist(role);
    }


    @Override
    public Role find(int id, Class<Role> className) {
        logger.info("find by id " + className + " id " + id);
        return entityManager.find(className, id);
    }

    @Override
    public void remove(Role role) {
        logger.info("remove " + role.getClass() + " id " + role.getId());
        entityManager.remove(entityManager.merge(role));
    }

    @Override
    public void merge(Role role) {
        logger.info("merge " + role.getClass() + " id " + role.getId());
        entityManager.merge(role);
    }

    @Override
    public List<Role> getAll(Class<Role> className) {
        logger.info("find all " + className);
        return entityManager.
                createQuery("from "+className.getSimpleName(), className).
                getResultList();
    }

    @Override
    public Role getRoleByName(String roleName) {
        return entityManager.
                createQuery("from Role as role where role.name=:name", Role.class).
                setParameter("name", roleName).getSingleResult();
    }
}
