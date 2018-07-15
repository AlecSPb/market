package ru.tsystem.javaschool.ordinaalena.DTO;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CustomerDTO implements Serializable {
    private Integer id;
    public CustomerDTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    private  String email;

    private  String parole;

    transient private  String paroleConfirm;

    private  String firstName;

    private  String secondName;

    private  String phonenumber;

    private List<String> roles;

    public CustomerDTO() {}

    public CustomerDTO(String email, String parole, String firstName, String secondName,
                   String phonenumber, List<String> roles) {
        this.email = email;
        this.parole = parole;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phonenumber = phonenumber;
        this.roles = roles;
    }

    public CustomerDTO(Integer id, String email, String parole, String firstName,
                       String secondName, String phonenumber, List<String> roles) {
        this.id=id;
        this.email = email;
        this.parole= parole;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phonenumber = phonenumber;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public String getParole() {
        return parole;
    }

    public String getParoleConfirm() {
        return paroleConfirm;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public List<String> getRole() {
        return roles;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setParole(String parole) {
        this.parole = parole;
    }

    public void setParoleConfirm (String paroleConfirm) {
        this.paroleConfirm = paroleConfirm;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setRole(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO customerDTO = (CustomerDTO) o;
        return  Objects.equals(email, customerDTO.email) &&
                Objects.equals(parole, customerDTO.parole) &&
                Objects.equals(firstName, customerDTO.firstName) &&
                Objects.equals(secondName, customerDTO.secondName) &&
                Objects.equals(phonenumber, customerDTO.phonenumber);
    }

    @Override
    public String toString() {
        return this.email + " " +
                this.parole + " " +
                this.firstName + " " +
                this.secondName + " " +
                this.phonenumber + " ";
    }


}
