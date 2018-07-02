package ru.tsystem.javaschool.ordinaalena.models;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "parameter")
public class Parameter {
    private int id;
    private String parameterName;


    public Parameter(){}

    @OneToMany(mappedBy = "parameter")
    private Set<ProductParameter> productParameter;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "parameter_name")
    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parameter that = (Parameter) o;

        if (id != that.id) return false;
        if (parameterName != null ? !parameterName.equals(that.parameterName) : that.parameterName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (parameterName != null ? parameterName.hashCode() : 0);
        return result;
    }
}
