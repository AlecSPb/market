
package ru.tsystem.javaschool.ordinaalena.DAO.impl;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.tsystem.javaschool.ordinaalena.DAO.api.AddressDAO;
import ru.tsystem.javaschool.ordinaalena.entities.Address;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AddressDAOImpl implements AddressDAO {

    private static final Logger logger = Logger.getLogger(AddressDAOImpl.class);


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void persist(final Address address){

        logger.info("persist new " + address.getClass());

        this.entityManager.persist(address);
    }


    @Override
    public void remove(final Address address) {
        logger.info("remove " + address.getClass() + " id " + address.getId());
        this.entityManager.remove(entityManager.merge(address));
    }


    @Override
    public Address find(int id, Class<Address> className) {
        logger.info("find by id " + className + " id " + id);
        return (Address) this.entityManager.find(className, id);
    }


    @Override
    public void merge(Address address){
        logger.info("merge " + address.getClass() + " id " + address.getId());
        this.entityManager.merge(address);
    }

    @Override
    public List<Address> getAll(Class<Address> className){
        logger.info("find all " + className);
        return this.entityManager.
                createQuery("from "+className.getSimpleName(), className).
                getResultList();
    }

    @Override
    public List<Address> getByClientId(int id) {
        return this.entityManager.
                createQuery("from Address as adr where adr.customer.id=:id" ,Address.class).
                setParameter("id", id).getResultList();
    }
}