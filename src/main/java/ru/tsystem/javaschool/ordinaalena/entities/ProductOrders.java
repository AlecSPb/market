package ru.tsystem.javaschool.ordinaalena.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_orders")
public class ProductOrders implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @Id
    @OneToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @Column(name = "product_count")
    private Integer count;

    public ProductOrders() { }

    public Product getProductId() {
        return productId;
    }

    public Orders getOrdersId() {
        return orders;
    }

    public int getCount() {
        return count;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public void setOrdersId(Orders ordersId) {
        this.orders = ordersId;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
