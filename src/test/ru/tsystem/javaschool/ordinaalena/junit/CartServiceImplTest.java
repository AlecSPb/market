package ru.tsystem.javaschool.ordinaalena.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.tsystem.javaschool.ordinaalena.DAO.api.ProductDAO;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;
import ru.tsystem.javaschool.ordinaalena.converter.Converter;
import ru.tsystem.javaschool.ordinaalena.entities.Product;
import ru.tsystem.javaschool.ordinaalena.services.api.CartService;

import java.util.ArrayList;
import java.util.List;

import static ru.tsystem.javaschool.ordinaalena.junit.DataTest.EMAIL;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring.xml")
public class CartServiceImplTest {
   @Autowired
    private CartService cartService;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private Converter converter;

    public CartServiceImplTest() {

    }

    @Test
    public void deleteAddToBucket(){
        Product product=productDAO.find(13,Product.class);
        List<ProductDTO> bagProducts=new ArrayList<>();
        cartService.addToCart(product.getId(), bagProducts);
        assertTrue(bagProducts.size()>0);
        cartService.deleteFromCart(bagProducts, product.getId());
        assertEquals(bagProducts.size(),0);

    }
}
