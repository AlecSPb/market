package ru.tsystem.javaschool.ordinaalena.DAO.impl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.tsystem.javaschool.ordinaalena.DAO.api.CustomerDAO;
import ru.tsystem.javaschool.ordinaalena.entities.Customer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerDAOImpl  implements CustomerDAO {
    private static final Logger logger = Logger.getLogger(AddressDAOImpl.class);
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public void persist(Customer customer) {
        logger.info("persist new " + customer.getClass());

        this.entityManager.persist(customer);
    }

    @Override
    public Customer find(int id, Class<Customer> className) {
        logger.info("find by id " + className + " id " + id);
        return (Customer) this.entityManager.find(className, id);
    }

    @Override
    public void remove(Customer customer) {
        logger.info("remove " + customer.getClass() + " id " + customer.getId());
        this.entityManager.remove(entityManager.merge(customer));
    }

    @Override
    public void merge(Customer customer) {
        logger.info("merge " + customer.getClass() + " id " + customer.getId());
        this.entityManager.merge(customer);
    }
    @Override
    public List<Customer> getAll(Class<Customer> className){
        logger.info("find all " + className);
        return this.entityManager.
                createQuery("from "+className.getSimpleName(), className).
                getResultList();
    }
    @Override
    public Customer getByEmail(String email) {
        Query query = entityManager.
                createQuery("from Customer as cl where cl.email=:email",
                        Customer.class).setParameter("email", email);
        return (Customer) query.getSingleResult();
    }

    @Override
    public int getCustomerIdByEmail(String email) {
        return entityManager.createQuery("select id from Customer as user where user.email=:email",
                Integer.class).setParameter("email", email).getSingleResult();
    }
}