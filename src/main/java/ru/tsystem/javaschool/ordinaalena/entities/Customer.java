package ru.tsystem.javaschool.ordinaalena.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="customer")
public class Customer {
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
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "parole")
    private String parole;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "secondname")
    private String secondName;

    @Column(name = "phonenumber")
    private String phonenumber;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

    @ManyToMany
    @JoinTable( name="customer_roles",
            joinColumns = @JoinColumn(name="customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Customer(){}

    public Customer(String email, String parole, String firstName,
                String secondName, String phonenumber) {
        this.email = email;
        this.parole = parole;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public String getParole() {
        return parole;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setParole(String parole) {
        this.parole = parole;
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

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
       Customer customer= (Customer) o;
        return Objects.equals(email, customer.email) &&
                Objects.equals(parole, customer.parole) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(secondName, customer.secondName) &&
                Objects.equals(phonenumber, customer.phonenumber);
    }
}
