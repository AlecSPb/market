package ru.tsystem.javaschool.ordinaalena.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
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
    @Column(name = "role_name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Customer> customers;

    public Role(){}

    public String getName() {
        return name;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setName(String roleName) {
        this.name = roleName;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
