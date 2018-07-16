package ru.tsystem.javaschool.ordinaalena.DTO;

public class Register {
    private ProductDTO productDTO;

    private int count;

    public Register() {
    }

    public Register(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public Register(ProductDTO productDTO, int count) {
        this.productDTO = productDTO;
        this.count = count;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public void setProductTitle(ProductDTO productTitle) {
        this.productDTO = productTitle;
    }

    public ProductDTO getProductTitle(){
        return productDTO;
    }

}
