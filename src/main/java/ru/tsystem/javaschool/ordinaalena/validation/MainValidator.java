package ru.tsystem.javaschool.ordinaalena.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import ru.tsystem.javaschool.ordinaalena.DTO.AddressDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.CustomerDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.OrdersDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;

import java.util.List;

public interface MainValidator extends Validator {
    void validateEmail(CustomerDTO customerDTO, Errors errors);
    void validateParoleSet(CustomerDTO customerDTO, Errors errors);
    void validateCustomerDetails(CustomerDTO customerDTO, Errors errors);
    void validateAddress(AddressDTO addressDTO, Errors errors);
    void validateNumbers(List<String> numbers, Errors errors);
    void validateProduct(ProductDTO productDTO, Errors errors);
    void validateCounts(OrdersDTO ordersDTO, Errors errors);
    void validateFile(MultipartFile file, Errors errors);
    void validateCountry (AddressDTO addressDTO,Errors errors);
    void validateRegion(AddressDTO addressDTO,Errors errors);
    void validateCity(AddressDTO addressDTO,Errors errors);
    void emailIsFree(CustomerDTO customerDTO,Errors errors);
    void validateStreet(AddressDTO addressDTO,Errors errors);
    void validateBuilding(AddressDTO addressDTO,Errors errors);
    void validateApartment(AddressDTO addressDTO,Errors errors);
    void validatePostcode (AddressDTO addressDTO,Errors errors);
}
