package ru.tsystem.javaschool.ordinaalena.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.tsystem.javaschool.ordinaalena.DAO.api.ProductDAO;
import ru.tsystem.javaschool.ordinaalena.entities.Product;
import ru.tsystem.javaschool.ordinaalena.services.api.AdminService;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring.xml")
public class AdminServiceImplTest {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ProductDAO productDAO;

    public AdminServiceImplTest() {

    }

    @Test
    public void changeProductStatusTest(){
        Product productBefore=productDAO.find(8,Product.class);
        boolean notavailable=productBefore.isNotavailable();
        adminService.changeProductStatus(productBefore.getId());
        Product productAfter=productDAO.find(8,Product.class);
        assertEquals(!notavailable,productAfter.isNotavailable());
    }

}
