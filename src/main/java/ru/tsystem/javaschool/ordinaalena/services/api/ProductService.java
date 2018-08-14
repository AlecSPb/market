package ru.tsystem.javaschool.ordinaalena.services.api;

import org.springframework.web.multipart.MultipartFile;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;
import ru.tsystem.javaschool.ordinaalena.entities.Product;

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
     List<ProductDTO> getById(Integer[] ids);

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
     ProductDTO getById(Integer id);
     List<ProductDTO> findTopProduct();
     List<Product> getTops();
     List<ProductDTO> convertProductsToProductsDTO(List<Product> products);
     void sendUpdateMessageToJmsServer();
    /**
     * Method checks if top is changed.
     *
     * @return true if is changed and false if not.
     */

     void updateTopIfItHaveChanged();
     void setProductDiff(Integer id, ProductDTO product);




}


