package ru.tsystem.javaschool.ordinaalena.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.tsystem.javaschool.ordinaalena.DTO.OrdersDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;
import ru.tsystem.javaschool.ordinaalena.services.api.CartService;
import ru.tsystem.javaschool.ordinaalena.services.api.CustomerService;
import ru.tsystem.javaschool.ordinaalena.services.api.OrdersService;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static ru.tsystem.javaschool.ordinaalena.junit.DataTest.EMAIL;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring.xml")
public class OrdersServiceImplTest {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private CustomerService customerService;

    public OrdersServiceImplTest() {

    }

    @Test
    public void makeNewOrderTest(){
        OrdersDTO ordersDTOBefore=new OrdersDTO();
        ordersDTOBefore.setCustomerEmail(EMAIL);
        ordersDTOBefore.setPaymentMethod("OFFLINE_CASH");
        ordersDTOBefore.setAddress(customerService.getCustomerAddresses(EMAIL).get(0));
        List<ProductDTO> bagProducts=new ArrayList<>();
        cartService.addToCart(13,  bagProducts);
        ordersDTOBefore.setProductDtos(bagProducts);
        List<String> counts=new ArrayList<>();
        counts.add(String.valueOf(bagProducts.size()));
        ordersDTOBefore.setCounts(counts);
        ordersService.makeNewOrder(ordersDTOBefore);
        OrdersDTO ordersDTOAfter=ordersService.getOrder(ordersDTOBefore.getId());
        assertEquals(ordersDTOBefore.getProductDTOs(),ordersDTOAfter.getProductDTOs());

    }
}
