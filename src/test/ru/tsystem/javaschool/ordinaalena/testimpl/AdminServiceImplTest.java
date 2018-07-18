package ru.tsystem.javaschool.ordinaalena.testimpl;

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
    private AdminService adminService;
    private ProductDAO productDAO;
    @Autowired
    public AdminServiceImplTest(AdminService adminService, ProductDAO productDAO) {
        this.adminService = adminService;
        this.productDAO = productDAO;
    }

    @Test
    public void changeProductStatusTest(){
        Product productBefore=productDAO.find(2,Product.class);
        boolean notavailable=productBefore.isNotavailable();
        adminService.changeProductStatus(productBefore.getId());
        Product productAfter=productDAO.find(2,Product.class);
        assertEquals(!notavailable,productAfter.isNotavailable());
    }

}
