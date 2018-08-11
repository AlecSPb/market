package ru.tsystem.javaschool.ordinaalena.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Address entity model. This class maps on addresses Table in our Database.
 * There we put all necessary options in fields. Hibernate forces us to make class
 * with fields and getters and setters for all of them and empty constructor, if we define custom one.
 */
@Entity
@Table(name = "address")
public class Address {
    /**
     * Address ID. It generates by Hibernate while inserting.
     * This field connects with id column in addresses table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    /**
     * Customer ID.
     * This field connects with customer_id column in addresses table.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * Address postcode number.
     * This field connects with postcode column in addresses table.
     * May be repeated.
     */
    @Column(name = "postcode")
    private int postcode;
    /**
     * Address country name.
     * This field connects with country column in addresses table.
     * May be repeated.
     */
    @Column(name = "country")
    private String country;
    /**
     * Address region name.
     * This field connects with region column in addresses table.
     * May be repeated.
     */
    @Column(name = "region")
    private String region;
    /**
     * Address city name.
     * This field connects with city column in addresses table.
     * May be repeated.
     */
    @Column(name = "city")
    private String city;
    /**
     * Address street name.
     * This field connects with street column in addresses table.
     * May be repeated.
     */
    @Column(name = "street")
    private String street;
    /**
     * Address building name.
     * This field connects with building column in addresses table.
     * May be repeated.
     */
    @Column(name = "building")
    private String building;
    /**
     * Address apartment number.
     * This field connects with apartment column in addresses table.
     * May be repeated.
     */
    @Column(name = "apartment")
    private String apartment;

    /**
     * Empty constructor for Hibernate.
     */
    public Address() {
    }

    /**
     * Our custom constructor.
     *
     * @param postcode  number to set
     * @param country   name to set
     * @param region    name to set
     * @param city      name to set
     * @param street    name to set
     * @param building  name to set
     * @param apartment number to set
     */
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

    /**
     * Simple getter
     *
     * @return Address id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Simple setter
     *
     * @param id value to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Simple getter
     *
     * @return Customer object
     */
    public Customer getCustomer() {
        return customer;

    }

    /**
     * Simple setter
     *
     * @param customer is object to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Simple getter
     *
     * @return Address postcode
     */
    public int getPostcode() {
        return postcode;
    }

    /**
     * Simple getter
     *
     * @return Address country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Simple getter
     *
     * @return Address region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Simple getter
     *
     * @return Address city
     */
    public String getCity() {
        return city;
    }

    /**
     * Simple getter
     *
     * @return Address building
     */
    public String getBuilding() {
        return building;
    }

    /**
     * Simple getter
     *
     * @return Address apartment
     */
    public String getApartment() {
        return apartment;
    }

    /**
     * Simple getter
     *
     * @return Address street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Simple setter
     *
     * @param postcode value to set
     */
    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    /**
     * Simple setter
     *
     * @param country value to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Simple setter
     *
     * @param region value to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Simple setter
     *
     * @param city value to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Simple setter
     *
     * @param building value to set
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * Simple setter
     *
     * @param apartment value to set
     */
    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    /**
     * Simple setter
     *
     * @param street value to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Compares the equality of the current object
     * with the object of same type
     *
     * @param o Object
     * @return boolean
     */
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

    /**
     * @return the hash value for this Object
     */
    @Override
    public int hashCode() {

        return Objects.hash(id, customer, postcode, country, region, city, street, building, apartment);
    }
}
