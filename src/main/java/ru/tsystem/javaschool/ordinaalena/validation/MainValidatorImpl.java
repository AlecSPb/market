package ru.tsystem.javaschool.ordinaalena.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import ru.tsystem.javaschool.ordinaalena.DTO.AddressDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.CustomerDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.OrdersDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;
import ru.tsystem.javaschool.ordinaalena.services.api.CustomerService;

import java.util.List;
@Component
public class MainValidatorImpl implements MainValidator{
        @Autowired
        private CustomerService customerService;

        @Override
        public boolean supports(Class<?> aClass) {
            return CustomerDTO.class.equals(aClass);
        }

        @Override
        public void validate(Object o, Errors errors) {
            throw new UnsupportedOperationException();
        }
    @Override
    public void validateEmail(CustomerDTO customerDTO, Errors errors) {
        if(!Patterns.emailPattern(customerDTO.getEmail()))
            errors.rejectValue("email", "Invalid.registration.email");
        if(customerService.getCustomer(customerDTO.getEmail())!=null)
            errors.rejectValue("email", "Duplicate.userForm.username");
    }

    @Override
    public void validateParoleSet(CustomerDTO customerDTO, Errors errors) {
        if(!Patterns.parolePattern(customerDTO.getParole()))
            errors.rejectValue("parole", "Invalid.registration.parole");
        if(!customerDTO.getParole().equals(customerDTO.getParoleConfirm()))
            errors.rejectValue("paroleConfirm", "Different.registration.paroleConfirm");
    }

    @Override
    public void validateCustomerDetails(CustomerDTO customerDTO, Errors errors) {
        if(!Patterns.namePattern(customerDTO.getSecondName()))
            errors.rejectValue("secondName", "Invalid.details.surname");
        if(!Patterns.namePattern(customerDTO.getFirstName()))
            errors.rejectValue("firstName", "Invalid.details.name");
        if(!Patterns.phonenumberPattern(customerDTO.getPhonenumber()))
            errors.rejectValue("phonenumber", "Invalid.details.phone");
    }

    @Override
    public void validateAddress(AddressDTO addressDTO, Errors errors) {
        if(!Patterns.localityPattern(addressDTO.getCountry()))
            errors.rejectValue("country", "Invalid.address.country");
        if(!Patterns.localityPattern(addressDTO.getRegion()))
            errors.rejectValue("region", "Invalid.address.region");
        if(!Patterns.localityPattern(addressDTO.getCity()))
            errors.rejectValue("city", "Invalid.address.city");
        if(!Patterns.namePattern(addressDTO.getStreet()))
            errors.rejectValue("street", "Invalid.address.street");
        if(!Patterns.homePattern(addressDTO.getBuilding()))
            errors.rejectValue("building", "Invalid.address.building");
        if(!Patterns.homePattern(addressDTO.getApartment()))
            errors.rejectValue("apartment", "Invalid.address.apartment");
        if(!Patterns.addressIndexPattern(addressDTO.getPostcode()))
            errors.rejectValue("postcode", "Invalid.address.postcode");
    }

    @Override
    public void validateNumbers(List<String> numbers, Errors errors) {
        if(numbers.isEmpty())
            errors.rejectValue("counts", "Invalid.integer.value");
        for(String number : numbers){
            if(!Patterns.numbersPattern(number)) {
                errors.rejectValue("counts", "Invalid.integer.value");
                break;
            }
        }
    }
    @Override
    public void validateCounts(OrdersDTO ordersDTO, Errors errors){
        for(int i=0; i<ordersDTO.getCounts().size(); i++){
            if(Integer.valueOf(ordersDTO.getCounts().get(i))>ordersDTO.getProductDTOs().get(i).getCount()) {
                errors.rejectValue("counts", "Invalid.order.count");
                break;
            }
        }
    }
    @Override
    public void validateProduct(ProductDTO productDTO, Errors errors) {
        if(!Patterns.namePattern(productDTO.getTitle()))
            errors.rejectValue("title", "Invalid.product.name");
        if(!Patterns.namePattern(productDTO.getCategory()))
            errors.rejectValue("category", "Invalid.product.category");
        if(!Patterns.namePattern(productDTO.getProductParameterDTO().getColor()))
            errors.rejectValue("productParameterDto.color", "Invalid.product.color");
    }



    @Override
    public void validateFile(MultipartFile file, Errors errors) {
        if(file == null)
            errors.rejectValue("picture", "Invalid.file");
    }
}
