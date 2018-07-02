package ru.tsystem.javaschool.ordinaalena.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    private int id;
    private String categoryName;
    private Integer parentCategory;

    public Category (){}

    @OneToMany(mappedBy = "category")
    private Set<Product> product;

    @OneToMany(mappedBy = "category")
    private Set<Parameter> parameter;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "category_name")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Basic
    @Column(name = "parent_category")
    public Integer getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Integer parentCategory) {
        this.parentCategory = parentCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category that = (Category) o;

        if (id != that.id) return false;
        if (categoryName != null ? !categoryName.equals(that.categoryName) : that.categoryName != null) return false;
        if (parentCategory != null ? !parentCategory.equals(that.parentCategory) : that.parentCategory != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        result = 31 * result + (parentCategory != null ? parentCategory.hashCode() : 0);
        return result;
    }
}
