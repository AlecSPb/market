package ru.tsystem.javaschool.ordinaalena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;
import ru.tsystem.javaschool.ordinaalena.services.api.CartService;
import ru.tsystem.javaschool.ordinaalena.services.api.PictureService;
import ru.tsystem.javaschool.ordinaalena.services.api.ProductService;
import ru.tsystem.javaschool.ordinaalena.services.api.SecurityService;

import java.util.List;

@RestController
public class ProductRestController {
    @Autowired
    SecurityService securityService;

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    PictureService puctureService;

    /**
     * Get all products
     * @param sortedBy      sorting by field
     * @param categories    categories of products
     * @param page          page
     * @return              List with products
     */
    @RequestMapping(value = "/productsRest", method = RequestMethod.GET)
    public List<ProductDTO> productsGet(String sortedBy, String[] categories, Integer page) {
        return productService.getAll(sortedBy, categories, page);
    }

    /**
     * Retrun count of pages.
     * @param categories    product categories
     * @return              page's count
     */
    @RequestMapping(value = "/pagesCount", method = RequestMethod.GET)
    public long getPagesCount(String[] categories) {
        return productService.getPagesCount(categories);
    }


}
