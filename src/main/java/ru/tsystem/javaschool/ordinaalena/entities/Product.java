package ru.tsystem.javaschool.ordinaalena.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product implements Serializable {
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
    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column
    private String category;

    @ManyToMany(mappedBy = "products")
    private Set<Orders> orders = new HashSet<>();

    @Column(name = "description")
    private String description;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parameter_id", unique = true)
    private ProductParameter parameter;

    @Column (name = "count")
    private int count;

    @Column(name = "notavailable")
    private boolean notavailable;

    public Product(){}

    public Product(String title, int price,
                   String category, int count, String description, ProductParameter parameter) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.count = count;
        this.description = description;
        this.parameter = parameter;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public ProductParameter getParameter() {
        return parameter;
    }

    public int getCount() {
        return count;
    }

    public String getTitle(){
        return title;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public String getDescription() {
        return description;
    }

    public boolean isNotavailable() {
        return notavailable;
    }
    public void setNotavailable(boolean notavailable) {
        this.notavailable = notavailable;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setParameter(ProductParameter parameterId) {
        this.parameter = parameterId;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price &&
                count == product.count &&
                Objects.equals(title, product.title) &&
                Objects.equals(category, product.category);
    }

}
