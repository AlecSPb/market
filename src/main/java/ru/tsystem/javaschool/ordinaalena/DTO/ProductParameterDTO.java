package ru.tsystem.javaschool.ordinaalena.DTO;

import java.util.Objects;

public class ProductParameterDTO {
    private Integer id;
    public ProductParameterDTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    private String brand;

    private String color;

    private Integer weight;


    public ProductParameterDTO() {
    }

    public ProductParameterDTO(String brand, String color,
                               Integer weight) {
        this.brand = brand;
        this.color = color;
        this.weight=weight;

    }

    public ProductParameterDTO(Integer id, String brand, String color,
                               Integer weight) {
        this.id=id;
        this.brand = brand;
        this.color = color;
        this.weight=weight;

    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }


    public Integer getWeight() {
        return weight;
    }



    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductParameterDTO that = (ProductParameterDTO) o;
        return Objects.equals(brand, that.brand) &&
                Objects.equals(color, that.color) &&
                Objects.equals(weight, that.weight);

    }
}
