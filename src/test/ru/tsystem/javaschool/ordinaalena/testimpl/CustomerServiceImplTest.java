package ru.tsystem.javaschool.ordinaalena.testimpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.tsystem.javaschool.ordinaalena.DTO.AddressDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.CustomerDTO;
import ru.tsystem.javaschool.ordinaalena.services.api.CustomerService;

import javax.swing.plaf.synth.Region;
import java.lang.management.BufferPoolMXBean;

import static ru.tsystem.javaschool.ordinaalena.testimpl.DataTest.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring.xml"})
public class CustomerServiceImplTest {

    private CustomerService customerService;

    @Autowired
    public CustomerServiceImplTest(CustomerService customerService) {
        this.customerService = customerService;
    }
    @Test
    public void getCustomer(){
        CustomerDTO returnedCustomer=customerService.getCustomer(EMAIL);
        assertEquals(EMAIL,returnedCustomer.getEmail());
        assertEquals(PAROLE,returnedCustomer.getParole());
        assertEquals(FIRSTNAME,returnedCustomer.getFirstName());
        assertEquals(SECONDNAME,returnedCustomer.getSecondName());
        assertEquals(PHONE,returnedCustomer.getPhonenumber());
    }

    @Test
    public void getAddCustomerAddress(){
        AddressDTO addressDTO=new AddressDTO();
        addressDTO.setCountry(COUNTRY);
        addressDTO.setRegion(REGION);
        addressDTO.setCity(CITY);
        addressDTO.setStreet(STREET);
        addressDTO.setBuilding(BUILDING);
        addressDTO.setApartment(APARTMENT);
        addressDTO.setPostcode(POSTCODE);

        customerService.addCustomerAddress(EMAIL,addressDTO);
        addressDTO =customerService.getCustomerAddress(EMAIL, addressDTO.getId());
        assertEquals(COUNTRY,addressDTO.getCountry());
        assertEquals(REGION,addressDTO.getRegion());
        assertEquals(CITY,addressDTO.getCity());
        assertEquals(STREET,addressDTO.getStreet());
        assertEquals(BUILDING,addressDTO.getBuilding());
        assertEquals(APARTMENT,addressDTO.getApartment());
        assertEquals(POSTCODE,addressDTO.getPostcode());
    }
}
