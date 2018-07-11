package ru.tsystem.javaschool.ordinaalena.controller;

import constants.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import ru.tsystem.javaschool.ordinaalena.DTO.AddressDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.OrdersDTO;
import ru.tsystem.javaschool.ordinaalena.services.api.*;
import ru.tsystem.javaschool.ordinaalena.validation.MainValidator;

@Controller
public class OrdersController {
    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @Autowired
    SecurityService securityService;

    @Autowired
    CartService cartService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    MainValidator validator;

   // @Autowired
  //  MQProducerService mqService;

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
        OrdersDTO ordersDTO= new OrdersDTO();
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
            return "/makeOrder";
        }

        validator.validateCounts(ordersDTO, bindingResult);
        if (bindingResult.hasErrors()) {
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
            model.addAttribute("error","Адресс не выбран!");
            model.addAttribute("addresses", customerService.getCustomerAddresses(ordersDTO.getCustomerEmail()));
            model.addAttribute("orderAddress", new AddressDTO());
            return "/setAddress";
        }
        orderAddress = customerService.getCustomerAddress(ordersDTO.getCustomerEmail(), orderAddress.getId());
        ordersDTO.setAddress(orderAddress);
        ordersDTO.setId(null);
        model.addAttribute("paymentMethods", PaymentMethod.values());
        model.addAttribute("ordersDTO", ordersDTO);
        return "/confirm";
    }

    /**
     * Part of creating order.
     * @param model         page model
     * @param ordersDTO      orderDto contains by session.
     * @return              page.
     */
    @RequestMapping(value = "/order_confirm", method = RequestMethod.POST)
    public String confirm(@ModelAttribute("ordersDTO") OrdersDTO ordersDTO, SessionStatus status, Model model){

        if(PaymentMethod.valueOfOrNull(ordersDTO.getPaymentMethod())==null) {
            model.addAttribute("error","Способ оплаты не выбран!");
            model.addAttribute("paymentMethods", PaymentMethod.values());
            model.addAttribute("ordersDTO", ordersDTO);
            return "/confirm";
        }
        ordersService.makeNewOrder(ordersDTO);

        status.setComplete();
//        mqService.ProduceMessage(orderDto.getId());


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
