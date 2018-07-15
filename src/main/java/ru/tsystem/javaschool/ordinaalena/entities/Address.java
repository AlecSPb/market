package ru.tsystem.javaschool.ordinaalena.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="address")
public class Address {
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
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "postcode")
    private int postcode;

    @Column(name = "country")
    private String country;

    @Column(name = "region")
    private String region;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "building")
    private String building;

    @Column(name = "apartment")
    private String apartment;

    public Address(){}

    public Address(int postcode, String country, String region,
                   String city, String street, String building,
                   String apartment) {
        this.postcode = postcode;
        this.country = country;
        this.region = region;
        this.city = city;
        this.building = building;
        this.apartment = apartment;
        this.street = street;
    }

    public Customer getCustomer() {
        return customer;
    }


    public int getPostcode() {
        return postcode;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public String getBuilding() {
        return building;
    }

    public String getApartment() {
        return apartment;
    }

    public String getStreet() {
        return street;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return postcode == address.postcode &&
                Objects.equals(country, address.country) &&
                Objects.equals(region, address.region) &&
                Objects.equals(city, address.city) &&
                Objects.equals(building, address.building) &&
                Objects.equals(apartment, address.apartment);
    }


}
