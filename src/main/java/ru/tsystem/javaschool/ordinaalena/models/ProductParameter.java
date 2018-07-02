package ru.tsystem.javaschool.ordinaalena.models;

import javax.persistence.*;

@Entity
@Table(name = "product_parameter")
public class ProductParameter {
    private int id;
    private int parameterId;
    private String parameterValue;

    public ProductParameter(){}

    @ManyToOne
    @JoinColumn(name = "parameter_id")
    private Parameter parameter;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "parameter_id")
    public int getParameterId() {
        return parameterId;
    }

    public void setParameterId(int parameterId) {
        this.parameterId = parameterId;
    }

    @Basic
    @Column(name = "parameter_value")
    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductParameter that = (ProductParameter) o;

        if (id != that.id) return false;
        if (parameterId != that.parameterId) return false;
        if (parameterValue != null ? !parameterValue.equals(that.parameterValue) : that.parameterValue != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + parameterId;
        result = 31 * result + (parameterValue != null ? parameterValue.hashCode() : 0);
        return result;
    }
}
