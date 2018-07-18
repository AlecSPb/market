package ru.tsystem.javaschool.ordinaalena.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductParameterDTO;
import ru.tsystem.javaschool.ordinaalena.services.api.*;
import ru.tsystem.javaschool.ordinaalena.validation.MainValidator;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    private OrdersService ordersService;

    private CustomerService customerService;

    private ProductService productService;

    private SecurityService securityService;

    private MainValidator validator;

    @Autowired
    public AdminController(AdminService adminService, OrdersService ordersService, CustomerService customerService, ProductService productService, SecurityService securityService, MainValidator validator) {
        this.adminService = adminService;
        this.ordersService = ordersService;
        this.customerService = customerService;
        this.productService = productService;
        this.securityService = securityService;
        this.validator = validator;
    }

    private static final Logger logger = Logger.getLogger(AdminController.class);

    /**
     * Administration controller's method which return admin page.
     * @return      jsp.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getAdminPage(){
        logger.info("admin: " + securityService.findLoggedInEmail());
        return "/admin";
    }

    /**
     * Administration controller's method which return page with all customers.
     * @param model page model.
     * @return      jsp.
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(Model model){
        logger.info("admin:" + securityService.findLoggedInEmail());
        model.addAttribute("customer", adminService.getCustomers());
        return "/users";
    }

    /**
     * Administration controller's method which page with customer's addresses.
     * @param model page model.
     * @param email user email.
     * @return      jsp.
     */
    @RequestMapping(value = "/addresses", method = RequestMethod.GET)
    public String getAddresses(Model model, String email){
       logger.info("admin:" + securityService.findLoggedInEmail());
        model.addAttribute("addresses",customerService.getCustomerAddresses(email));
        model.addAttribute("email",email);
        return "/addresses";
    }

    /**
     * Administration controller's method which page with customer's orders.
     * @param model page model.
     * @param email user email.
     * @return      jsp.
     */
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getOrders(Model model, String email){
        logger.info("admin: " + securityService.findLoggedInEmail());
        model.addAttribute("orders",ordersService.getCustomerOrders(email));
        model.addAttribute("email",email);
        return "/orders";
    }

    /**
     * Administration controller's method which page where new products added.
     * @param model page model.
     * @return      jsp.
     */
    @RequestMapping(value = "/add_new_product", method = RequestMethod.GET)
    public String getNewProduct(Model model){
        logger.info("admin: " + securityService.findLoggedInEmail());
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("productParameter", new ProductParameterDTO());
        return "/addProduct";
    }

    /**
     * Administration controller's method which save new product.
     * @param model page model.
     * @return      jsp.
     */
    @RequestMapping(value = "/add_new_product", method = RequestMethod.POST)
    public String addPic(@RequestBody MultipartFile picture,
                         @ModelAttribute ProductDTO productDTO,
                         BindingResult bindingResult,
                         Model model){

        logger.info("Add new product. Admin: " + securityService.findLoggedInEmail());
        validator.validateProduct(productDTO, bindingResult);
        validator.validateFile(picture, bindingResult);

        if(bindingResult.hasErrors()){
            model.addAttribute("product", new ProductDTO());
            model.addAttribute("productParameter", new ProductParameterDTO());
            return "/addProduct";
        }

        productService.addProduct(productDTO, picture);
        return "redirect:/products";
    }


    /**
     * Administration controller's method which return page with all products.
     * @param model page model.
     * @return      jsp.
     */
    @RequestMapping(value = "/production", method = RequestMethod.GET)
    public String getProducts(Model model){
        logger.info("admin: " + securityService.findLoggedInEmail());
        model.addAttribute("products", adminService.getProducts());
        return "/production";
    }

    /**
     * Administration controller's method which return page with statistic.
     * @param model page model.
     * @return      jsp.
     */
    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public String getStatistic(Model model){
        logger.info("admin: " + securityService.findLoggedInEmail());
        model.addAttribute("products", ordersService.getAllBuyingCounts());
        model.addAttribute("customer", ordersService.getAllCustomersCounts());
        return "/statistic";
    }

}
