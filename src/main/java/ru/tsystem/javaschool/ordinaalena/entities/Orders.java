package ru.tsystem.javaschool.ordinaalena.entities;

import ru.tsystem.javaschool.ordinaalena.constants.OrderStatus;
import ru.tsystem.javaschool.ordinaalena.constants.PaymentMethod;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Address address;

    @Column(name = "payment_method")
    private String paymentMethod;

    //    @Enumerated(EnumType.STRING)
    @Column(name = "orders_status")
    private String orderStatus;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_orders",
            joinColumns = { @JoinColumn(name = "orders_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    private Set<Product> products = new HashSet<>();


    public Orders(){}

    public Orders(PaymentMethod paymentMethod, OrderStatus orderStatus, Address address) {
        this.paymentMethod = PaymentMethod.getString(paymentMethod);
        this.orderStatus = orderStatus.toString();
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders order = (Orders) o;
        return Objects.equals(paymentMethod, order.paymentMethod) &&
                Objects.equals(orderStatus, order.orderStatus) &&
                Objects.equals(products, order.products);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Address getAddress() {
        return address;
    }

    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.valueOfOrNull(paymentMethod);
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = PaymentMethod.getString(paymentMethod);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus.toString();
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
