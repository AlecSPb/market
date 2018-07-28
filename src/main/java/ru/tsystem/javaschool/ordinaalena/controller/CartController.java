package ru.tsystem.javaschool.ordinaalena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tsystem.javaschool.ordinaalena.DTO.OrdersDTO;
import ru.tsystem.javaschool.ordinaalena.entities.Product;
import ru.tsystem.javaschool.ordinaalena.services.api.CartService;
import ru.tsystem.javaschool.ordinaalena.services.api.ProductService;
import ru.tsystem.javaschool.ordinaalena.services.api.SecurityService;

@Controller
@RequestMapping("/bucket")
public class CartController {

    private CartService cartService;

    private SecurityService securityService;
    private ProductService productService;

    @Autowired
    public CartController(CartService cartService, SecurityService securityService,ProductService productService) {
        this.cartService = cartService;
        this.securityService = securityService;
        this.productService=productService;
    }


    /**
     * Return page with bucket.
     * @param model     page model
     * @param error     errors for validation
     * @return          jsp
     */
    @RequestMapping(method = RequestMethod.GET)
    public String cartGet(Model model, String error){
        String customerEmail = securityService.findLoggedInEmail();
        OrdersDTO cart = cartService.getCustomerCart(customerEmail);
        if(error!=null)
            model.addAttribute("error", error);
        model.addAttribute("products", cart.getProductDTOs());
        model.addAttribute("productsSize", cart.getProductDTOs().size());

        return "/bucket";
     /*   Product product = productService.findProductById(Long.parseLong(id), false);
        Object bag = request.getSession().getAttribute("bag");
        if (bag == null) {
            ArrayList<BagProductDto> bagProducts = new ArrayList<>();
            bagService.addToBag(product.getId(),
                    Integer.parseInt(amount),
                    Long.parseLong(sizeId),
                    Long.parseLong(product.getPrice()),
                    bagProducts);
            request.getSession().setAttribute("bag", bagProducts);
        } else {
            bagService.addToBag(product.getId(),
                    Integer.parseInt(amount),
                    Long.parseLong(sizeId),
                    Long.parseLong(product.getPrice()),
                    (List<BagProductDto>) bag);
        }
        return "redirect:/catalog/" + id;*/
    }

    /**
     * Delete products from bucket.
     * @param selected     errors for validation
     * @return             jsp
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteFromBucket(String[] selected){
        if(selected==null)
            return "redirect:/bucket?error='No one is selected'";
        String customerEmail = securityService.findLoggedInEmail();
        cartService.deleteFromCart(customerEmail, selected);
        return "redirect:/bucket";
    }
}
