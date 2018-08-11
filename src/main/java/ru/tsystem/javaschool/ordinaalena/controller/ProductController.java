package ru.tsystem.javaschool.ordinaalena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.tsystem.javaschool.ordinaalena.services.api.ProductService;


@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController (ProductService productService) {

        this.productService = productService;
    }


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
    public String getProduct(Model model, String title){
        model.addAttribute("product", productService.getByTitle(title));
        return "/product";
    }
}
