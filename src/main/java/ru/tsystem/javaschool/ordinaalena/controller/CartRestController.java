package ru.tsystem.javaschool.ordinaalena.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tsystem.javaschool.ordinaalena.services.api.CartService;
import ru.tsystem.javaschool.ordinaalena.services.api.SecurityService;

@RestController
public class CartRestController {
    @Autowired
    SecurityService securityService;

    @Autowired
    CartService cartService;

    /**
     * Add product to bucket
     * @param prodId    product id.
     */
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public void addProductToBucket(@RequestParam Integer prodId){
        String customerEmail = securityService.findLoggedInEmail();
        cartService.addToCart(customerEmail, prodId);
    }
}
