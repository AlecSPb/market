package ru.tsystem.javaschool.ordinaalena.testimpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.tsystem.javaschool.ordinaalena.DAO.api.ProductDAO;
import ru.tsystem.javaschool.ordinaalena.entities.Product;
import ru.tsystem.javaschool.ordinaalena.services.api.CartService;

import static ru.tsystem.javaschool.ordinaalena.testimpl.DataTest.EMAIL;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring.xml")
public class CartServiceImplTest {
    private CartService cartService;
    private ProductDAO productDAO;
    @Autowired
    public CartServiceImplTest(CartService cartService, ProductDAO productDAO) {
        this.cartService = cartService;
        this.productDAO = productDAO;
    }

    @Test
    public void deleteAddToBucket(){
        Product product=productDAO.find(1,Product.class);
        cartService.addToCart(EMAIL,product.getId());
        assertTrue(cartService.getCustomerCart(EMAIL).getProductDTOs().size()>0);
        cartService.deleteFromCart(EMAIL,product.getTitle());
        assertEquals(cartService.getCustomerCart(EMAIL).getProductDTOs().size(),0);

    }
}
