package ru.tsystem.javaschool.ordinaalena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystem.javaschool.ordinaalena.DTO.OrdersDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;
import ru.tsystem.javaschool.ordinaalena.entities.Product;
import ru.tsystem.javaschool.ordinaalena.services.api.CartService;
import ru.tsystem.javaschool.ordinaalena.services.api.ProductService;
import ru.tsystem.javaschool.ordinaalena.services.api.SecurityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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
    public ModelAndView cartGet(Model model, String error, HttpServletRequest request) {
        ModelAndView modelAndView=new ModelAndView("bucket");
        modelAndView.addObject("bucket",request.getSession().getAttribute("bucket"));
        if(error!=null)
            modelAndView.addObject("error", error);

        return modelAndView;
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
    public String deleteFromBucket(Integer[] selected, HttpSession session){
        if(selected==null)
            return "redirect:/bucket?error='No one is selected'";
        //String customerEmail = securityService.findLoggedInEmail();
        List<ProductDTO> bucket= (List<ProductDTO>)session.getAttribute("bucket");
        cartService.deleteFromCart(bucket,selected);
        return "redirect:/bucket";
    }
}
