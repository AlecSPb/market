package ru.tsystem.javaschool.ordinaalena.controller;

import ru.tsystem.javaschool.ordinaalena.constants.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import ru.tsystem.javaschool.ordinaalena.DTO.AddressDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.OrdersDTO;
import ru.tsystem.javaschool.ordinaalena.services.api.*;
import ru.tsystem.javaschool.ordinaalena.validation.MainValidator;

@Controller
@RequestMapping("/order")
@SessionAttributes(types = {OrdersDTO.class})
public class OrdersController {

    private ProductService productService;

    private CustomerService customerService;

    private SecurityService securityService;

    private OrdersService ordersService;

    private MainValidator validator;

    @Autowired
    public OrdersController(ProductService productService, CustomerService customerService, SecurityService securityService, OrdersService ordersService, MainValidator validator) {
        this.productService = productService;
        this.customerService = customerService;
        this.securityService = securityService;
        this.ordersService = ordersService;
        this.validator = validator;
    }

    /**
     * Part of creating order.
     * @param model     page model
     * @param selected  Products which selected in bucket
     * @return          page.
     */
    @RequestMapping(value = "/make_order", method = RequestMethod.GET)
    public String confirmOrder(Model model, String[] selected){
        if(selected == null)
            return "redirect:/bucket?error='No one is selected'";
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setProductDtos(productService.getByTitles(selected));
        model.addAttribute("ordersDTO", ordersDTO);
        return "/makeOrder";
    }

    /**
     * Part of creating order.
     * @param model         page model
     * @param ordersDTO      orderDto contains by session.
     * @param bindingResult Errors
     * @return              page.
     */
    @RequestMapping(value = "/set_count", method = RequestMethod.POST)
    public String setOrderCount(OrdersDTO ordersDTO, Model model, BindingResult bindingResult){

        validator.validateNumbers(ordersDTO.getCounts(), bindingResult);
        if(bindingResult.hasErrors()) {
            model.addAttribute("error","Enter the quantity");
            return "/makeOrder";
        }

        validator.validateCounts(ordersDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("error","Such quantity is not available!");
           return "/makeOrder";
        }

        ordersDTO.setCustomerEmail(securityService.findLoggedInEmail());

        model.addAttribute("addresses", customerService.getCustomerAddresses(ordersDTO.getCustomerEmail()));
        model.addAttribute("orderAddress", new AddressDTO());
        return "/setAddress";
    }

    /**
     * Part of creating order.
     * @param model         page model
     * @param ordersDTO     orderDto contains by session.
     * @return              page.
     */
    @RequestMapping(value = "/set_address", method = RequestMethod.POST)
    public String setAddresses(AddressDTO orderAddress, Model model, OrdersDTO ordersDTO){
        if(orderAddress.getId()==null) {
            model.addAttribute("error","Select address!");
            model.addAttribute("addresses", customerService.getCustomerAddresses(ordersDTO.getCustomerEmail()));
            model.addAttribute("orderAddress", new AddressDTO());
            return "/setAddress";
        }
        orderAddress = customerService.getCustomerAddress(ordersDTO.getCustomerEmail(), orderAddress.getId());
        ordersDTO.setAddress(orderAddress);
        ordersDTO.setId(null);
        model.addAttribute("paymentMethods", PaymentMethod.values());
        model.addAttribute("orderDto", ordersDTO);
        return "/confirm";
    }

    /**
     * Part of creating order.
     * @param model         page model
     * @param ordersDTO     orderDto contains by session.
     * @return              page.
     */
    @RequestMapping(value = "/order_confirm", method = RequestMethod.POST)
    public String confirm(@ModelAttribute("orderDto") OrdersDTO ordersDTO, SessionStatus status, Model model){

        if(PaymentMethod.valueOfOrNull(ordersDTO.getPaymentMethod())==null) {
            model.addAttribute("error","Payment method don't selected!");
            model.addAttribute("paymentMethods", PaymentMethod.values());
            model.addAttribute("orderDto", ordersDTO);
            return "/confirm";
        }
        ordersService.makeNewOrder(ordersDTO);

        status.setComplete();



        return "redirect:/bucket";
    }

    /**
     * Return history page
     * @param model     page mode
     * @return          jsp
     */
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String getHistory(Model model){
        String loggedUserEmail = securityService.findLoggedInEmail();
        model.addAttribute("orders", ordersService.getCustomerOrders(loggedUserEmail));
        return "/history";
    }

}
