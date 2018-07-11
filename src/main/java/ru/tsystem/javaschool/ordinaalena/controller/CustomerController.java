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
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private MainValidator mainValidator;


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
      // mainValidator.validateEmail(customerDTO, bindingResult);
     // mainValidator.validateParoleSet(customerDTO, bindingResult);

        if (bindingResult.hasErrors())
            return "/registration";

        customerService.registrationCustomer(customerDTO);

        securityService.autoLogin(customerDTO.getEmail(), customerDTO.getParole());
        return "redirect:/home";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account(Model model) {
        String loggedCustomerEmail = securityService.findLoggedInEmail();
        //need to persist some out message if cant to remove address!

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
    public String addAddress(@ModelAttribute("address") AddressDTO address, BindingResult bindingResult) {
        mainValidator.validateAddress(address, bindingResult);

        if (bindingResult.hasErrors())
            return "/addAddress";

        String customerEmail = securityService.findLoggedInEmail();

        customerService.addCustomerAddress(customerEmail, address);

        return "redirect:/account";
    }
    /**
     * return change account details page
     * @param model     page model
     * @return          jsp
     */
    @RequestMapping(value = "change_details", method = RequestMethod.GET)
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
    @RequestMapping(value = "change_details", method = RequestMethod.POST)
    public String setAccountDetails(@ModelAttribute("customer") CustomerDTO customer,
                                    BindingResult bindingResult) {
        mainValidator.validateCustomerDetails(customer, bindingResult);

        if (bindingResult.hasErrors())
            return "/setAccountDetails";

        String loggedUserEmail = securityService.findLoggedInEmail();
        customerService.setCustomerDifferents(loggedUserEmail, customer);
        return "redirect:/account";
    }

    /**
     * return change password page
     * @param model     page model
     * @return          jsp
     */
    @RequestMapping(value = "change_parole", method = RequestMethod.GET)
    public String ChangePassword(Model model) {
        model.addAttribute("customer", new CustomerDTO());
        return "/changePassword";
    }

    /**
     * Change details
     * @param customer             new user with new password
     * @param bindingResult     errors
     * @return                  redirect to account if success
     */
    @RequestMapping(value = "change_parole", method = RequestMethod.POST)
    public String setChangedPassword(@ModelAttribute("customer") CustomerDTO customer, BindingResult bindingResult) {
        mainValidator.validateParoleSet(customer, bindingResult);
        if (bindingResult.hasErrors())
            return "/changeParole";

        String loggedCustomerEmail = securityService.findLoggedInEmail();
        customerService.changeParole(loggedCustomerEmail, customer.getParole());
        return "redirect:/account";
    }
}
