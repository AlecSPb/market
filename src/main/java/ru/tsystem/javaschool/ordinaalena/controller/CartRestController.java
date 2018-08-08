package ru.tsystem.javaschool.ordinaalena.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;
import ru.tsystem.javaschool.ordinaalena.entities.Orders;
import ru.tsystem.javaschool.ordinaalena.entities.Product;
import ru.tsystem.javaschool.ordinaalena.services.api.CartService;
import ru.tsystem.javaschool.ordinaalena.services.api.OrdersService;
import ru.tsystem.javaschool.ordinaalena.services.api.ProductService;
import ru.tsystem.javaschool.ordinaalena.services.api.SecurityService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/bucket")
public class  CartRestController {

    private SecurityService securityService;

    private CartService cartService;
    private ProductService productService;


    @Autowired
    public CartRestController(SecurityService securityService, CartService cartService, ProductService productService) {
        this.securityService = securityService;
        this.cartService = cartService;
        this.productService=productService;
    }

    /**
     * Add product to bucket
     * @param prodId    product id.
     */
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public void addProductToBucket(@RequestParam Integer prodId, HttpServletRequest request){
        ProductDTO product=productService.getById(prodId);
        Object bucket=request.getSession().getAttribute("bucket");
        if(bucket==null) {
            ArrayList<ProductDTO> bagProducts = new ArrayList<>();
            cartService.addToCart(product.getId(), bagProducts);
            request.getSession().setAttribute("bucket", bagProducts);
        }
        else {
            cartService.addToCart(product.getId(),(List<ProductDTO> )bucket);
        }
    }
}
