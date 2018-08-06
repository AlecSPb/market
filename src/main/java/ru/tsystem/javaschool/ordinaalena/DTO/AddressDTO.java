package ru.tsystem.javaschool.ordinaalena.DTO;

import java.util.Objects;

public class AddressDTO {
    private Integer id;
    public AddressDTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    private String  postcode;

    private String country;

    private String region;

    private String city;

    private String street;

    private String building;

    private String apartment;

    public AddressDTO() {
    }

    public AddressDTO(String postcode, String country, String region,
                      String city, String street, String building, String apartment) {
        this.postcode = postcode;
        this.country = country;
        this.region = region;
        this.city = city;
        this.building = building;
        this.apartment = apartment;
        this.street = street;
    }

    public AddressDTO(Integer id, String postcode, String country, String region,
                      String city, String street,String building, String apartment) {

        this.id=id;
        this.postcode = postcode;
        this.country = country;
        this.region = region;
        this.city = city;
        this.building = building;
        this.apartment = apartment;
        this.street = street;
    }

    public String getPostcode() {
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

    public void setPostcode(String postcode) {
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
        AddressDTO that = (AddressDTO) o;
        return Objects.equals(postcode, that.postcode) &&
                Objects.equals(country, that.country) &&
                Objects.equals(region, that.region) &&
                Objects.equals(city, that.city) &&
                Objects.equals(street, that.street) &&
                Objects.equals(building, that.building) &&
                Objects.equals(apartment, that.apartment);
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "id=" + id +
                ", postcode='" + postcode + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", apartment='" + apartment + '\'' +
                '}';
    }
}
