
package ru.tsystem.javaschool.ordinaalena.DAO.impl;

import ru.tsystem.javaschool.ordinaalena.constants.OrderStatus;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.tsystem.javaschool.ordinaalena.DAO.api.OrdersDAO;
import ru.tsystem.javaschool.ordinaalena.entities.Orders;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrdersDAOImpl implements OrdersDAO {
    private static final Logger logger=Logger.getLogger(OrdersDAOImpl.class);
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public void persist(Orders orders) {
        logger.info("persist new"+orders.getClass());
        entityManager.persist(orders);

    }

    @Override
    public Orders find(int id, Class<Orders> className) {
        logger.info("find by id"+className+"id"+id);
        return entityManager.find(className,id);
    }

    @Override
    public void remove(Orders orders) {
        logger.info("remove " + orders.getClass() + " id " + orders.getId());
        entityManager.remove(entityManager.merge(orders));
    }

    @Override
    public void merge(Orders orders) {
        logger.info("merge " + orders.getClass() + " id " +orders.getId());
        entityManager.merge(orders);
    }

    @Override
    public List<Orders> getAll(Class<Orders> className) {
        logger.info("find all " + className);
        return entityManager.
                createQuery("from "+className.getSimpleName(), className).
                getResultList();
    }

    @Override
    public Orders getClientBucket(int clientId) {
        return entityManager.createQuery("from Orders as ord where ord.orderStatus=:bucket and ord.customer.id=:id",
                Orders.class)
                .setParameter("bucket", OrderStatus.BUCKET.toString())
                .setParameter("id",clientId)
                .getSingleResult();
    }

    @Override
    public List<Orders> getClientOrders(int clientId) {
        return entityManager.createQuery("from Orders as ord where ord.orderStatus != :bucket and ord.customer.id = :id"
                , Orders.class)
                .setParameter("bucket", OrderStatus.BUCKET.toString())
                .setParameter("id",clientId).getResultList();
    }

    @Override
    public List<Orders> ordersWithAddresses(int addressId) {
        return entityManager.createQuery("from Orders as order where order.address.id=:id"
                , Orders.class)
                .setParameter("id", addressId)
                .getResultList();
    }

    @Override
    public long getProductCounts(int productId) {
        return entityManager.createQuery("SELECT sum(prord.count)" +
                "                          FROM ProductOrders as prord" +
                "                          where prord.orders.id IN" +
                "                                (SELECT id FROM Orders as ord where" +
                "                                  ord.id = prord.orders.id and" +
                "                                  prord.productId.id = :prid and"+
                "                                  ord.orderStatus = 'UNDONE' or" +
                "                                  ord.orderStatus = 'DONE')",Long.class)
                .setParameter("prid", productId).getSingleResult();
    }

    @Override
    public long getUserBuyingCounts(int customerId) {
        return entityManager.createQuery("SELECT sum(prord.count)" +
                "                          FROM ProductOrders as prord" +
                "                          where prord.orders.id IN" +
                "                                (SELECT id FROM Orders as ord where" +
                "                                  ord.id = prord.orders.id and" +
                "                                  ord.customer.id = :customerId and" +
                "                                  ord.orderStatus = 'UNDONE' or" +
                "                                   ord.orderStatus = 'DONE') ", Long.class)
                .setParameter("customerId", customerId)
                .getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Orders> findOrdersByCustomerId(int customerId) {

        return entityManager.createQuery("SELECT c FROM Orders c WHERE c.customer.id= :customerId")

                    .setParameter("customerId", customerId).getResultList();



    }
}