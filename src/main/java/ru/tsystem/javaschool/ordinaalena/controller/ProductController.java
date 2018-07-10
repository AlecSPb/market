package ru.tsystem.javaschool.ordinaalena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tsystem.javaschool.ordinaalena.services.api.CartService;
import ru.tsystem.javaschool.ordinaalena.services.api.PictureService;
import ru.tsystem.javaschool.ordinaalena.services.api.ProductService;
import ru.tsystem.javaschool.ordinaalena.services.api.SecurityService;

@Controller
public class ProductController {
    @Autowired
    SecurityService securityService;

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    PictureService pictureService;

    /**
     * Return page with all products
     * @param model     page model
     * @return          jsp
     */
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String productsGet(Model model) {
        model.addAttribute("categories", productService.getAllCategories());
        return "/products";
    }

    /**
     * Return page with selected product.
     * @param model         page model.
     * @param productTitle   product name
     * @return              jsp
     */
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String getProduct(Model model, String productTitle){
        model.addAttribute("product", productService.getByTitle(productTitle));
        return "/product";
    }
}
