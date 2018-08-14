package ru.tsystem.javaschool.ordinaalena.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;
import ru.tsystem.javaschool.ordinaalena.entities.Product;
import ru.tsystem.javaschool.ordinaalena.services.api.ProductService;
import ru.tsystem.javaschool.ordinaalena.validation.MainValidator;


@Controller
public class ProductController {

    private ProductService productService;
    private MainValidator mainValidator;
    private static final Logger logger=Logger.getLogger(ProductController.class);

    @Autowired
    public ProductController (ProductService productService,MainValidator mainValidator) {

        this.productService = productService;
        this.mainValidator=mainValidator;
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
     * @param id  product id
     * @return              jsp
     */
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String getProduct(Model model,Integer id){
        model.addAttribute("product", productService.getById(id));
        model.addAttribute("id",id);
        return "/product";
    }
    @RequestMapping(value = "/product/{id}",method = RequestMethod.POST)
    public String changeProductDetails(@ModelAttribute("product" )ProductDTO productDTO,
                                       @PathVariable(name = "id") Integer id, Model model,
                                       BindingResult bindingResult){
        ProductDTO product=productService.getById(id);
        mainValidator.validateProduct(product,bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("error","Details is incorrect");
            return "/product";
        }

       productService.setProductDiff(id,productDTO);
       try {
            productService.updateTopIfItHaveChanged();
            //log
            logger.info("System has sent message to ActiveMQ.");
        } catch (JmsException e) {
            //log
            logger.info("System has tried to send message to ActiveMQ server, but something was wrong.", e);
        }
        return "redirect:/admin/production";
    }
}
