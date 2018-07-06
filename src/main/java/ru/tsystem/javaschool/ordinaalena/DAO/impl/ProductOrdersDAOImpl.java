
package ru.tsystem.javaschool.ordinaalena.DAO.impl;

import ru.tsystem.javaschool.ordinaalena.DAO.api.ProductOrdersDAO;
import ru.tsystem.javaschool.ordinaalena.entities.ProductOrders;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ProductOrdersDAOImpl implements ProductOrdersDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProductOrders get(int productId, int ordersId) {
        return entityManager.
                createQuery("from ProductOrders as op where op.orders.id=:orderId and op.productId.id=:productId",
                        ProductOrders.class)
                .setParameter("orderId", ordersId)
                .setParameter("productId", productId)
                .getSingleResult();
    }

    @Override
    public void merge(ProductOrders productOrders) {
        entityManager.merge(productOrders);
    }
}