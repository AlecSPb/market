package ru.tsystem.javaschool.ordinaalena.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystem.javaschool.ordinaalena.DAO.api.CustomerDAO;
import ru.tsystem.javaschool.ordinaalena.entities.Customer;
import ru.tsystem.javaschool.ordinaalena.entities.Role;

import java.util.HashSet;
import java.util.Set;

public class CustomerDetailsServiceImpl  implements UserDetailsService {

    private CustomerDAO customerDAO;
    @Autowired
    public CustomerDetailsServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    private static final Logger logger = Logger.getLogger(CustomerDetailsServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email)  {

        logger.info("email: " + email);

        Customer customer = customerDAO.getByEmail(email);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for(Role role: customer.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.
                User(customer.getEmail(), customer.getParole(), grantedAuthorities);
    }
    }

