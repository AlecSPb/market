package ru.tsystem.javaschool.ordinaalena.testimpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.tsystem.javaschool.ordinaalena.DTO.OrdersDTO;
import ru.tsystem.javaschool.ordinaalena.services.api.CartService;
import ru.tsystem.javaschool.ordinaalena.services.api.CustomerService;
import ru.tsystem.javaschool.ordinaalena.services.api.OrdersService;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static ru.tsystem.javaschool.ordinaalena.testimpl.DataTest.EMAIL;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring.xml")
public class OrdersServiceImplTest {
    private CartService cartService;
    private OrdersService ordersService;
    private CustomerService customerService;
    @Autowired
    public OrdersServiceImplTest(CartService cartService, OrdersService ordersService, CustomerService customerService) {
        this.cartService = cartService;
        this.ordersService = ordersService;
        this.customerService = customerService;
    }

    @Test
    public void makeNewOrderTest(){
        OrdersDTO ordersDTOBefore=new OrdersDTO();
        ordersDTOBefore.setCustomerEmail(EMAIL);
        ordersDTOBefore.setPaymentMethod("OFFLINE_CASH");
        ordersDTOBefore.setAddress(customerService.getCustomerAddresses(EMAIL).get(0));
        cartService.addToCart(EMAIL,1);
        ordersDTOBefore.setProductDtos(cartService.getCustomerCart(EMAIL).getProductDTOs());
        List<String> counts=new ArrayList<>();
        counts.add("1");
        ordersDTOBefore.setCounts(counts);
        ordersService.makeNewOrder(ordersDTOBefore);
        OrdersDTO ordersDTOAfter=ordersService.getOrder(ordersDTOBefore.getId());
        assertEquals(ordersDTOBefore,ordersDTOAfter);

    }
}
