
package ru.tsystem.javaschool.ordinaalena.DAO.api;

import ru.tsystem.javaschool.ordinaalena.entities.ProductOrders;

public interface ProductOrdersDAO {
    ProductOrders get(int productId, int ordersId);

    void merge(ProductOrders productOrders);
}
