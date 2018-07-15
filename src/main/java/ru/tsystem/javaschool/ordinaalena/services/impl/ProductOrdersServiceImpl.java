package ru.tsystem.javaschool.ordinaalena.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystem.javaschool.ordinaalena.DAO.api.ProductOrdersDAO;
import ru.tsystem.javaschool.ordinaalena.entities.ProductOrders;
import ru.tsystem.javaschool.ordinaalena.services.api.ProductOrdersService;
@Service
public class ProductOrdersServiceImpl implements ProductOrdersService {

    private ProductOrdersDAO productOrdersDAO;
    @Autowired
    public ProductOrdersServiceImpl(ProductOrdersDAO productOrdersDAO) {
        this.productOrdersDAO = productOrdersDAO;
    }

    @Override
    @Transactional
    public int getCount(int productId, int ordersId) {
        return productOrdersDAO.get(productId, ordersId).getCount();
    }

    @Override
    @Transactional
    public void setCount(int productId, int ordersId, int count) {
        ProductOrders productOrders = productOrdersDAO.get(productId,ordersId);
        productOrders.setCount(count);
        productOrdersDAO.merge(productOrders);
    }
}
