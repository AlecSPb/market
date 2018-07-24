package ru.tsystem.javaschool.ordinaalena.services.api;

import org.springframework.web.multipart.MultipartFile;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;

import java.util.List;

public interface ProductService {

    /**
     * Get all products which sorted with category.
     * @param sortedByField Sorted type.
     * @param categories    categories.
     * @return              List with products.
     */
     List<ProductDTO> getAll(String sortedByField, String[] categories, Integer page);

    /**
     * Return all categories (only different)
     * @return  String list with categories
     */
     List<String> getAllCategories();

    /**
     * Get product by product name which unique.
     * @param title  Product name.
     * @return      Product entity.
     */
     ProductDTO getByTitle (String title);

    /**
     * Get products by product name which unique.
     * @param titles  Product's names.
     * @return      Product's entities.
     */
     List<ProductDTO> getByTitles(String[] titles);

    /**
     * Add product to db.
     * @param productDTO    Product.
     */
     void addProduct(ProductDTO productDTO, MultipartFile picture);

    /**
     * Get table size.
     * @return  table size.
     */
     long getPagesCount(String[] categories);

}
