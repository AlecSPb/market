package ru.tsystem.javaschool.ordinaalena.services.api;

import ru.tsystem.javaschool.ordinaalena.DTO.AddressDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.CustomerDTO;

import java.util.List;

public interface CustomerService {
    /**
     * Create new user.
     * @param dto  Customer.
     */
    public void registrationUser(CustomerDTO dto );

    /**
     * Get user by email.
     * @param email User's email.
     * @return          User.
     */
    public CustomerDTO getCustomer (String email);

    /**
     * Get user's addresses.
     * @param email Customer's email.
     * @return          All customer's addresses.
     */
    public List<AddressDTO> getCustomerAddresses(String email);

    /**
     * Get customer's address.
     * @param email Customer's email who own address.
     * @param addressId Address id.
     * @return          Address.
     */
    public AddressDTO getCustomerAddress(String email, int addressId);

    /**
     * Add new Address for customer.
     * @param email Customer's email
     * @param address   Address.
     */
    public void addCustomerAddress(String email, AddressDTO address);

    /**
     * Set user's details.
     * @param email User's email.
     * @param customer      User details.
     */
    public void setCustomerDifferents(String email, CustomerDTO customer);

    /**
     * Delete address.
     * @param addressId Address id.
     * @param email User's email who own address.
     */
    public void deleteCustomerAddress(int addressId, String email);

    /**
     * Change parole for user.
     * @param email     User's email.
     * @param newParole  New password.
     */
    public void changeParole(String email, String newParole);
}

