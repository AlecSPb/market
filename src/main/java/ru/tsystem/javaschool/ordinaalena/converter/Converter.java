package ru.tsystem.javaschool.ordinaalena.converter;

import constants.OrderStatus;
import constants.PaymentMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Component;
import ru.tsystem.javaschool.ordinaalena.DTO.*;
import ru.tsystem.javaschool.ordinaalena.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Converter {
  // @Autowired
  // private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger logger = Logger.getLogger(Converter.class);
    /**
     * Convert CustomerDTO entity into CustomerDTO.
     *
     * @param customer Customer entity.
     * @return CustomerDTO.
     */

    public CustomerDTO convertToDTO (Customer customer) {
        logger.info("Customer " + customer.getId());
        Integer id = customer.getId();
        String email = customer.getEmail();
        String parole= customer.getParole();
        String firstName = customer.getFirstName();
        String secondName=customer.getSecondName();
        String phonenumber = customer.getPhonenumber();
      List<String> roles = roleConverter(customer.getRoles());

        return new CustomerDTO(id, email, parole, firstName, secondName,
                phonenumber,roles);
    }
    public Customer convertToEntity (CustomerDTO customerDTO){
       // logger.info("ProductDto ");
        String email = customerDTO.getEmail();
       // String parole = bCryptPasswordEncoder.encode(customerDTO.getParole());
       String parole=customerDTO.getParole();
        String firstName = customerDTO.getFirstName();
        String secondName = customerDTO.getSecondName();
        String phonenumber = customerDTO.getPhonenumber();

        return new Customer(email, parole, firstName, secondName, phonenumber);
    }
    /**
     * Convert AddressDTO entity into AddressDTO.
     *
     * @param address AddressDTO entity.
     * @return AddressDTO DTO.
     */
    public AddressDTO convertToDTO (Address address){
        if (address == null)
            return null;
        logger.info("Address " + address.getId());
        Integer id = address.getId();
        String postcode = Integer.toString(address.getPostcode());
        String country = address.getCountry();
        String region = address.getRegion();
        String city = address.getCity();
        String street = address.getStreet();
        String building = address.getBuilding();
        String apartment = address.getApartment();

        return new AddressDTO(id, postcode, country,
                region, city, street, building, apartment);
    }
    public Address convertToEntity (AddressDTO addressDTO){

        Integer postcode = Integer.valueOf(addressDTO.getPostcode());
        String country = addressDTO.getCountry();
        String region = addressDTO.getRegion();
        String city = addressDTO.getCity();
        String street = addressDTO.getStreet();
        String building = addressDTO.getBuilding();
        String apartment = addressDTO.getApartment();

        logger.info("Address ");

        return new Address(postcode, country, region, city, street, building, apartment);
    }
    public OrdersDTO convertToDTO (Orders orders){
        logger.info("Order " + orders.getId());
        Integer id = orders.getId();
        String email = orders.getCustomer().getEmail();
        String paymentMethod = PaymentMethod.getString(orders.getPaymentMethod());
        String orderStatus = orders.getOrderStatus().toString();
        List<ProductDTO> productDTOs = orders.getProducts().stream().
                map(product -> this.convertToDTO(product)).collect(Collectors.toList());
        AddressDTO addressDto = this.convertToDTO(orders.getAddress());

        return new OrdersDTO(id, email, paymentMethod, orderStatus, productDTOs, addressDto);
    }

    public Orders convertToEntity (OrdersDTO ordersDTO){
       // logger.info("ProductDto ");
        String paymentMethod = ordersDTO.getPaymentMethod();
        String orderStatus = ordersDTO.getOrderStatus();
        Address address = convertToEntity(ordersDTO.getAddress());

        return new Orders (PaymentMethod.valueOfOrNull(paymentMethod), OrderStatus.valueOf(orderStatus), address);
    }
    public ProductDTO convertToDTO (Product product){

        logger.info("Product " + product.getId());

        Integer id = product.getId();
        String title = product.getTitle();
        int price = product.getPrice();
        String category =product.getCategory();
        int count = product.getCount();
        String description = product.getDescription();
        ProductParameterDTO productParameterDTO = this.convertToDTO(product.getParameter());

        return new ProductDTO(id, title, price, category, count, description, productParameterDTO);
    }
    public Product convertToEntity(ProductDTO productDTO){
        logger.info("ProductDto ");
        String title = productDTO.getTitle();
        Integer price = productDTO.getPrice();
        String category = productDTO.getCategory();
        Integer count = productDTO.getCount();
        String description = productDTO.getDescription();
        ProductParameter productParameter = convertToEntity(productDTO.getProductParameterDTO());
        // Stream.of(name, cost, category, count, description).anyMatch()

        return new Product(title, price, category, count, description, productParameter);
    }
    public ProductParameterDTO convertToDTO(ProductParameter productParameter){
        logger.info("ProductParameter " + productParameter.getId());
        Integer id = productParameter.getId();
        String brand = productParameter.getBrand();
        String color = productParameter.getColor();
        int weight = productParameter.getWeight();

        return new ProductParameterDTO(id, brand, color, weight);
    }
    public ProductParameter convertToEntity (ProductParameterDTO productParameterDTO){
       // logger.info("ProductDto ");
        String brand = productParameterDTO.getBrand();
        String color = productParameterDTO.getColor();
        Integer weight = productParameterDTO.getWeight();

        return new ProductParameter(brand, color, weight);
    }
    private List<String> roleConverter(Set<Role> roles){
        List<String> list = new ArrayList<>();
        for (Role role : roles)
            list.add(role.getName());
        return list;
    }
}
