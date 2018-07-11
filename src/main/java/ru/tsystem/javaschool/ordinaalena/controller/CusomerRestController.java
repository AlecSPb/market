package ru.tsystem.javaschool.ordinaalena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tsystem.javaschool.ordinaalena.DTO.AddressDTO;
import ru.tsystem.javaschool.ordinaalena.services.api.CustomerService;
import ru.tsystem.javaschool.ordinaalena.services.api.OrdersService;
import ru.tsystem.javaschool.ordinaalena.services.api.SecurityService;
import ru.tsystem.javaschool.ordinaalena.validation.MainValidator;

public class CusomerRestController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    MainValidator validator;

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
