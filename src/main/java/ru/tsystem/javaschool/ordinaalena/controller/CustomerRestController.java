package ru.tsystem.javaschool.ordinaalena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.tsystem.javaschool.ordinaalena.DTO.AddressDTO;
import ru.tsystem.javaschool.ordinaalena.services.api.CustomerService;
import ru.tsystem.javaschool.ordinaalena.services.api.OrdersService;
import ru.tsystem.javaschool.ordinaalena.services.api.SecurityService;
import ru.tsystem.javaschool.ordinaalena.validation.MainValidator;
@RestController
@RequestMapping("/user")
public class CustomerRestController {


    private OrdersService ordersService;

    private CustomerService customerService;

    private SecurityService securityService;

    private MainValidator validator;

    @Autowired
    public CustomerRestController(OrdersService ordersService, CustomerService customerService, SecurityService securityService, MainValidator validator) {
        this.ordersService = ordersService;
        this.customerService = customerService;
        this.securityService = securityService;
        this.validator = validator;
    }

    /**
     * Delete address
     * @param addressId address id
     */
    @RequestMapping(value = "/addresses/{addressId}", method = RequestMethod.DELETE)
    public void deleteAddress(@PathVariable Integer addressId) {
        String loggedUserEmail = securityService.findLoggedInEmail();
        customerService.deleteCustomerAddress(addressId, loggedUserEmail);
    }

    /**
     * Create address
     * @param addressDTO        address id
     * @param bindingResult     errors
     */
    @RequestMapping(value = "/addresses", method = RequestMethod.POST)
    public Integer addAddress(@RequestBody AddressDTO addressDTO, BindingResult bindingResult) {
        validator.validateAddress(addressDTO, bindingResult);
        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException();
        }

        String loggedUserEmail = securityService.findLoggedInEmail();
        customerService.addCustomerAddress(loggedUserEmail, addressDTO);
        return addressDTO.getId();
    }
}
