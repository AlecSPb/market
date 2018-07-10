package ru.tsystem.javaschool.ordinaalena.services.api;

import ru.tsystem.javaschool.ordinaalena.DTO.OrdersDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;

import java.util.List;

public interface CartService {
    /**
     * Delete product from customer's bucket if it exist.
     *
     * @param email     Customer email.
     * @param productTitle  Product name which unique.
     */
    public void deleteFromCart(String email, String productTitle);

    /**
     * Delete product from customer's bucket if it exist.
     *
     * @param email     Customer email.
     * @param productTitles Product's names which unique.
     */
    public void deleteFromCart(String email, String[] productTitles);

    /**
     * Delete product from user's bucket if it exist.
     *
     * @param email     Customer email.
     * @param removeProducts  Products.
     */
    public void deleteFromCart(String email, List<ProductDTO> removeProducts);

    /**
     * Delete product from user's bucket if it exist.
     *
     * @param dto  Dto with products and userEmail.
     */
    public void deleteFromCart(OrdersDTO ordersDTO);

    /**
     * Add product to user's bucket if it isn't exist.
     *
     * @param email     Customer email.
     * @param productId     Product id which unique.
     */
    public void addToCart(String email, int productId);

    /**
     * Return bucket owned by user.
     *
     * @param email     Customer email.
     * @return          Bucket dto.
     */
    public OrdersDTO getCustomerCart(String email);

}
