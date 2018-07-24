package ru.tsystem.javaschool.ordinaalena.services.api;

import ru.tsystem.javaschool.ordinaalena.DTO.AddressDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.CustomerDTO;


import java.util.List;

public interface CustomerService {
    /**
     * Create new user.
     * @param dto  Customer.
     */
      void registrationCustomer(CustomerDTO dto );

    /**
     * Get user by email.
     * @param email User's email.
     * @return          User.
     */
     CustomerDTO getCustomer (String email);

    /**
     * Get user's addresses.
     * @param email Customer's email.
     * @return          All customer's addresses.
     */
     List<AddressDTO> getCustomerAddresses(String email);

    /**
     * Get customer's address.
     * @param email Customer's email who own address.
     * @param addressId Address id.
     * @return          Address.
     */
     AddressDTO getCustomerAddress(String email, int addressId);

    /**
     * Add new Address for customer.
     * @param email Customer's email
     * @param address   Address.
     */
     void addCustomerAddress(String email, AddressDTO address);

    /**
     * Set user's details.
     * @param email User's email.
     * @param customer      User details.
     */
     void setCustomerDifferents(String email, CustomerDTO customer);

    /**
     * Delete address.
     * @param addressId Address id.
     * @param email User's email who own address.
     */
     void deleteCustomerAddress(int addressId, String email);

    /**
     * Change parole for user.
     * @param email     User's email.
     * @param newParole  New password.
     */
     void changeParole(String email, String newParole);
}

