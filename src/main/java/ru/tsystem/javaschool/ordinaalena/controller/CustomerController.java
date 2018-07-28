package ru.tsystem.javaschool.ordinaalena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tsystem.javaschool.ordinaalena.DTO.AddressDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.CustomerDTO;
import ru.tsystem.javaschool.ordinaalena.services.api.CustomerService;

import ru.tsystem.javaschool.ordinaalena.services.api.SecurityService;
import ru.tsystem.javaschool.ordinaalena.validation.MainValidator;

@Controller
@RequestMapping("/user")
public class CustomerController {

    private CustomerService customerService;

    private SecurityService securityService;

    private MainValidator mainValidator;

    @Autowired
    public CustomerController(CustomerService customerService, SecurityService securityService, MainValidator mainValidator) {
        this.customerService = customerService;
        this.securityService = securityService;
        this.mainValidator = mainValidator;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getSignIn(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Email or password is incorrect");

        if (logout != null)
            model.addAttribute("message", "Logged outer successful");

        return "/login";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistration(Model model) {
        model.addAttribute("customer", new CustomerDTO());
        return "/registration";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("customer") CustomerDTO customerDTO,
                               BindingResult bindingResult, Model model) {
        mainValidator.validateEmail(customerDTO,bindingResult);
        if (bindingResult.hasErrors()){
                model.addAttribute("error","Email is incorrected");
            return "/registration";}
        mainValidator.validateParoleSet(customerDTO,bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("error","Passwords must match!");
            return "/registration";}
        customerService.registrationCustomer(customerDTO);
        securityService.autoLogin(customerDTO.getEmail(), customerDTO.getParole());
        return "redirect:/";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account(Model model) {
        String loggedCustomerEmail = securityService.findLoggedInEmail();
        model.addAttribute("customer", customerService.getCustomer(loggedCustomerEmail));
        model.addAttribute("addresses", customerService.getCustomerAddresses(loggedCustomerEmail));
        return "/account";
    }

    @RequestMapping(value = "/add_address", method = RequestMethod.GET)
    public String addAddress(Model model) {
        model.addAttribute("address", new AddressDTO());
        return "/addAddress";
    }
    /**
      * Create new address
     * @param address       new address
     * @param bindingResult Errors
     * @return              redirect to account if success
     */
    @RequestMapping(value = "/add_address", method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("address") AddressDTO address, Model model, BindingResult bindingResult) {
        mainValidator.validateCountry(address, bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("error","Country is incorrect!");
            return "/addAddress";}
        mainValidator.validateRegion(address, bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("error","Region is incorrect!");
            return "/addAddress";}
        mainValidator.validateCity(address, bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("error","City is incorrect!");
            return "/addAddress";}
        mainValidator.validateStreet(address, bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("error","Street is incorrect!");
            return "/addAddress";}
        mainValidator.validateBuilding(address, bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("error","Building is incorrect!");
            return "/addAddress";}
        mainValidator.validateApartment(address, bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("error","Apartment is incorrect!");
            return "/addAddress";}
        mainValidator.validatePostcode(address, bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("error","Postcode is incorrect!");
            return "/addAddress";}
        String customerEmail = securityService.findLoggedInEmail();

        customerService.addCustomerAddress(customerEmail, address);

        return "redirect:/user/account";
    }
    /**
     * return change account details page
     * @param model     page model
     * @return          jsp
     */
    @RequestMapping(value = "/change_details", method = RequestMethod.GET)
    public String getAccountDetails(Model model) {
        model.addAttribute("customer", new CustomerDTO());
        return "/setAccountDetails";
    }

    /**
     * Change details
     * @param customer             new user with new details
     * @param bindingResult     errors
     * @return                  redirect to account if success
     */
    @RequestMapping(value = "/change_details", method = RequestMethod.POST)
    public String setAccountDetails(@ModelAttribute("customer") CustomerDTO customer,Model model,
                                    BindingResult bindingResult) {
        mainValidator.validateCustomerDetails(customer, bindingResult);

        if (bindingResult.hasErrors()){
            model.addAttribute("error","Details is incorrect");
            return "/setAccountDetails";
        }


        String loggedUserEmail = securityService.findLoggedInEmail();
        customerService.setCustomerDifferents(loggedUserEmail, customer);
        return "redirect:/user/account";
    }

    /**
     * return change password page
     * @param model     page model
     * @return          jsp
     */
    @RequestMapping(value = "/change_parole", method = RequestMethod.GET)
    public String changePassword(Model model) {
        model.addAttribute("customer", new CustomerDTO());
        return "/changePassword";
    }

    /**
     * Change details
     * @param customer             new user with new password
     * @param bindingResult     errors
     * @return                  redirect to account if success
     */
    @RequestMapping(value = "/change_parole", method = RequestMethod.POST)
    public String setChangedPassword(@ModelAttribute("customer") CustomerDTO customer, BindingResult bindingResult,Model model) {
        mainValidator.validateParoleSet(customer, bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("error","Passwords must match!");
            return "/changePassword";}

        String loggedCustomerEmail = securityService.findLoggedInEmail();
        customerService.changeParole(loggedCustomerEmail, customer.getParole());
        return "redirect:/user/account";
    }
}
