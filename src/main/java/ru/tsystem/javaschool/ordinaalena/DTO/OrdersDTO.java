package ru.tsystem.javaschool.ordinaalena.DTO;

import java.util.List;
import java.util.Objects;

public class OrdersDTO {
    private Integer id;
    public OrdersDTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    private String customerEmail;

    private String paymentMethod;

    private String orderStatus;

    private AddressDTO address;

    private List<ProductDTO> productDTOs;

    private List<String> counts;

    public OrdersDTO() {
    }

    public OrdersDTO(String customerEmail, String paymentMethod,  String orderStatus, List<ProductDTO> productDtos,
                    AddressDTO address) {
        this.customerEmail = customerEmail;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
        this.productDTOs = productDTOs;
        this.address = address;
    }

    public OrdersDTO(Integer id, String customerEmail, String paymentMethod, String orderStatus, List<ProductDTO> productDTOs,
                    AddressDTO address) {
        this.id=id;
        this.customerEmail = customerEmail;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
        this.productDTOs = productDTOs;
        this.address = address;
    }

    public OrdersDTO(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public List<String> getCounts() {
        return counts;
    }

    public List<ProductDTO> getProductDTOs() {
        return productDTOs;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public void setProductDtos(List<ProductDTO> productDtOs) {
        this.productDTOs = productDTOs;
    }

    public void setCounts(List<String> counts) {
        this.counts = counts;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersDTO ordersDTO = (OrdersDTO) o;
        return Objects.equals(customerEmail, ordersDTO.customerEmail) &&
                Objects.equals(paymentMethod, ordersDTO.paymentMethod) &&
                Objects.equals(orderStatus, ordersDTO.orderStatus) &&
                Objects.equals(address, ordersDTO.address) &&
                Objects.equals(productDTOs, ordersDTO.productDTOs) &&
                Objects.equals(counts, ordersDTO.counts) ;
    }
}
