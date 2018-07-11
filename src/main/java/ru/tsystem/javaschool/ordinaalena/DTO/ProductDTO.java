package ru.tsystem.javaschool.ordinaalena.DTO;

import java.util.Objects;

public class ProductDTO {
    private Integer id;
    public ProductDTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String title;

    private Integer price;

    private String category;

    private Integer count;

    private String description;



    private ProductParameterDTO productParameterDTO;

    public ProductDTO() {
    }

    public ProductDTO(String title, Integer price, String category,
                      Integer count, String description,ProductParameterDTO productParameterDTO) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.count = count;
        this.description = description;
        this.productParameterDTO = productParameterDTO;
    }

    public ProductDTO(Integer id, String title, Integer price, String category,
                      Integer count, String description, ProductParameterDTO productParameterDTO) {
        this.id=id;
        this.title = title;
        this.price = price;
        this.category = category;
        this.count = count;
        this.description = description;
        this.productParameterDTO = productParameterDTO;
    }

    public Integer getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public Integer getCount() {
        return count;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ProductParameterDTO getProductParameterDTO() {
        return productParameterDTO;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProductParameterDTO(ProductParameterDTO productParameterDTO) {
        this.productParameterDTO = productParameterDTO;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return  Objects.equals(title, that.title) &&
                Objects.equals(price, that.price) &&
                Objects.equals(category, that.category) &&
                Objects.equals(description, that.description) &&
                Objects.equals(productParameterDTO, that.productParameterDTO);
    }
}
