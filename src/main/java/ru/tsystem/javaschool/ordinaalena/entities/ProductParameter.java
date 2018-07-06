package ru.tsystem.javaschool.ordinaalena.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_parameter")
public class ProductParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "brand")
    private String brand;

    @Column(name = "color")
    private String color;

    @Column(name = "weight")
    private int weight;


    public ProductParameter(){}

    public ProductParameter(String brand, String color,
                            int weight) {
        this.brand = brand;
        this.color = color;
        this.weight=weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductParameter that = (ProductParameter) o;
        return  weight == that.weight &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(color, that.color);
    }


    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }


    public int getWeight() {
        return weight;
    }


    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public void setWeight(int weight) {
        this.weight = weight;
    }


}
