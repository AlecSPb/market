
package ru.tsystem.javaschool.ordinaalena.DAO.api;

import ru.tsystem.javaschool.ordinaalena.entities.ProductOrders;

public interface ProductOrdersDAO {
    public ProductOrders get(int productId, int ordersId);

    public void merge(ProductOrders productOrders);
}
