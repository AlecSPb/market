package ru.tsystem.javaschool.ordinaalena.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import ru.tsystem.javaschool.ordinaalena.DAO.api.OrdersDAO;
import ru.tsystem.javaschool.ordinaalena.DTO.AddressDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.CustomerDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.OrdersDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;

import java.util.List;

public interface MainValidator extends Validator {
    public void validateEmail(CustomerDTO customerDTO, Errors errors);
    public void validateParoleSet(CustomerDTO customerDTO, Errors errors);
    public void validateCustomerDetails(CustomerDTO customerDTO, Errors errors);
    public void validateAddress(AddressDTO addressDTO, Errors errors);
    public void validateNumbers(List<String> numbers, Errors errors);
    public void validateProduct(ProductDTO productDTO, Errors errors);
    public void validateCounts(OrdersDTO ordersDTO, Errors errors);
    public void validateFile(MultipartFile file, Errors errors);
}
