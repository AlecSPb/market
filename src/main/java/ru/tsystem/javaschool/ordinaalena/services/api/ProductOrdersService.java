package ru.tsystem.javaschool.ordinaalena.services.api;

public interface ProductOrdersService {
    /**
     * Get count of same product in same order.
     * @param productId Product id.
     * @param ordersId   Order id.
     * @return          Product's count.
     */
    public int getCount(int productId, int ordersId);

    /**
     * Set count to the same product in same order.
     * @param productId Product id.
     * @param ordersId   Order id.
     * @param count     Count.
     */
    public void setCount(int productId, int ordersId, int count);
}
