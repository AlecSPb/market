package ru.tsystem.javaschool.ordinaalena.services.impl;

import ru.tsystem.javaschool.ordinaalena.constants.OrderStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystem.javaschool.ordinaalena.DAO.api.AddressDAO;
import ru.tsystem.javaschool.ordinaalena.DAO.api.CustomerDAO;
import ru.tsystem.javaschool.ordinaalena.DAO.api.OrdersDAO;
import ru.tsystem.javaschool.ordinaalena.DAO.api.RoleDAO;
import ru.tsystem.javaschool.ordinaalena.DTO.AddressDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.CustomerDTO;
import ru.tsystem.javaschool.ordinaalena.converter.Converter;
import ru.tsystem.javaschool.ordinaalena.entities.Address;
import ru.tsystem.javaschool.ordinaalena.entities.Customer;
import ru.tsystem.javaschool.ordinaalena.entities.Orders;
import ru.tsystem.javaschool.ordinaalena.entities.Role;
import ru.tsystem.javaschool.ordinaalena.services.api.CustomerService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;

    private RoleDAO roleDAO;

    private OrdersDAO ordersDAO;

    private AddressDAO addressDAO;

    private BCryptPasswordEncoder encoder;

    private Converter converter;

    private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class);

    @Autowired
    public CustomerServiceImpl(CustomerDAO customerDAO, RoleDAO roleDAO, OrdersDAO ordersDAO, AddressDAO addressDAO, BCryptPasswordEncoder encoder, Converter converter) {
        this.customerDAO = customerDAO;
        this.roleDAO = roleDAO;
        this.ordersDAO = ordersDAO;
        this.addressDAO = addressDAO;
        this.encoder = encoder;
        this.converter = converter;
    }

    @Override
    @Transactional
    public void registrationCustomer(CustomerDTO dto) {
        logger.info("registration: " + dto.getEmail());

        Customer customer = converter.convertToEntity(dto);
        Role role = roleDAO.getRoleByName("ROLE_USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        customer.setRoles(roleSet);
        customerDAO.persist(customer);
        Orders order = new Orders();
        order.setCustomer(customer);
        order.setOrderStatus(OrderStatus.BUCKET);
        ordersDAO.persist(order);
    }

    @Override
    @Transactional
    public CustomerDTO getCustomer(String email) {
       Customer customer = customerDAO.getByEmail(email);
        return converter.convertToDTO(customer);
    }

    @Override
    @Transactional
    public List<AddressDTO> getCustomerAddresses(String email) {
       Customer customer = customerDAO.getByEmail(email);
        return customer.getAddresses().stream()
                .map(address -> converter.convertToDTO(address))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AddressDTO getCustomerAddress(String email, int addressId) {
        Address address = addressDAO.find(addressId, Address.class);
        if(address.getCustomer().getEmail().equals(email))
            return converter.convertToDTO(address);
        else return null;
    }

    @Override
    @Transactional
    public void addCustomerAddress(String email, AddressDTO address) {
        Address addressEntity = converter.convertToEntity(address);
        Customer customer= customerDAO.getByEmail(email);
        addressEntity.setCustomer(customer);
        addressDAO.persist(addressEntity);
        address.setId(addressEntity.getId());
    }

    @Override
    @Transactional
    public void setCustomerDifferents(String email, CustomerDTO customer) {

        logger.info("change user. Old email: " + email);

       Customer customerEntity = customerDAO.getByEmail(email);
        setDif(customerEntity, customer);
        customerDAO.merge(customerEntity);
    }
    private Customer setDif(Customer customer, CustomerDTO customerDTO){
        if(customerDTO.getFirstName() != null)
            customer.setFirstName(customerDTO.getFirstName());
        if(customerDTO.getSecondName() != null)
            customer.setSecondName(customerDTO.getSecondName());
        if(customerDTO.getPhonenumber() != null)
            customer.setPhonenumber(customerDTO.getPhonenumber());
        return customer;
    }
    @Override
    @Transactional
    public void deleteCustomerAddress(int addressId, String email) {
        Address address = addressDAO.find(addressId, Address.class);

        if(address.getCustomer().getEmail().equals(email)) {
            address.setCustomer(null);
            addressDAO.merge(address);
        }
    }

    @Override
    @Transactional
    public void changeParole(String email, String newParole) {
        logger.info("change password. Email: " + email);

        Customer customer = customerDAO.getByEmail(email);
        customer.setParole(encoder.encode(newParole));
        customerDAO.merge(customer);
    }
}
