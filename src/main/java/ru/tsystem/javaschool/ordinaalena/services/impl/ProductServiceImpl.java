package ru.tsystem.javaschool.ordinaalena.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.tsystem.javaschool.ordinaalena.DAO.api.ProductDAO;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;
import ru.tsystem.javaschool.ordinaalena.converter.Converter;
import ru.tsystem.javaschool.ordinaalena.entities.Product;
import ru.tsystem.javaschool.ordinaalena.services.api.PictureService;
import ru.tsystem.javaschool.ordinaalena.services.api.ProductService;

import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    private Converter converter;

    private PictureService pictureService;
    private final JmsTemplate jmsTemplate;
    private List<Product> tops = new ArrayList<>();

    public List<Product> getTops() {
        return tops;
    }

    public void setTops(List<Product> tops) {
        this.tops = tops;
    }


    @Autowired
    public ProductServiceImpl(ProductDAO productDAO, Converter converter, PictureService pictureService, JmsTemplate jmsTemplate) {
        this.productDAO = productDAO;
        this.converter = converter;
        this.pictureService = pictureService;
        this.jmsTemplate = jmsTemplate;
    }

    private static final int PRODUCTS_ON_PAGE = 4;

    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

    @Override
    @Transactional
    public List<ProductDTO> getAll(String sortedByField, String[] categories, Integer page) {
        page = page == null ? 1 : page;
        List<ProductDTO> dtos = getProducts(categories).
                stream().map(product -> converter.convertToDTO(product))
                .collect(Collectors.toList());
        if (sortedByField != null) {
            if (sortedByField.equals("price"))
                dtos = sortByPrice(dtos);
            else if (sortedByField.equals("title"))
                dtos = sortByTitle(dtos);
        }

        int startIndex = (page - 1) * PRODUCTS_ON_PAGE;
        int endIndex = startIndex + PRODUCTS_ON_PAGE;

        if (endIndex >= dtos.size()) {
            dtos = dtos.subList(startIndex, dtos.size());
            return dtos;
        }
        return dtos.subList(startIndex, endIndex);
    }

    private List<Product> getProducts(String[] categories) {
        if (categories == null)
            return productDAO.getAll(Product.class);
        else
            return productDAO.getByCategories(categories);
    }


    private List<ProductDTO> sortByPrice(List<ProductDTO> productDTOs) {
        productDTOs.sort((ProductDTO a, ProductDTO b) -> {
            if (a.getPrice() > b.getPrice())
                return 1;
            if (a.getPrice() < b.getPrice())
                return -1;
            return 0;
        });
        return productDTOs;
    }

    private List<ProductDTO> sortByTitle(List<ProductDTO> productDTOS) {
        productDTOS.sort((ProductDTO a, ProductDTO b) -> {
            int compare = a.getTitle().compareTo(b.getTitle());
            if (compare > 0)
                return 1;
            if (compare < 0)
                return -1;
            return 0;
        });
        return productDTOS;
    }

    private long getProductsCount(String[] categories) {
        if (categories == null)
            return productDAO.getProductsCount();
        return productDAO.getProductsCount(categories);
    }

    @Override
    @Transactional
    public List<String> getAllCategories() {
        return productDAO.getCategories();
    }

    @Override
    @Transactional
    public ProductDTO getByTitle(String title) {
        Product product;
        product = productDAO.getByTitle(title);
        return converter.convertToDTO(product);
    }
    @Override
    @Transactional
    public ProductDTO getById(Integer id) {
        Product product;
        product = productDAO.getById(id);
        return converter.convertToDTO(product);
    }

    @Override
    @Transactional
    public List<ProductDTO> getById(Integer[] ids) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Integer id : ids) {
            productDTOS.add(converter.convertToDTO(productDAO.getById(id)));
        }
        return productDTOS;
    }

    @Override
    @Transactional
    public void addProduct(ProductDTO productDTO, MultipartFile picture) {
        logger.info("name - " + productDTO.getTitle());

        Product product = converter.convertToEntity(productDTO);
        productDAO.persist(product);

        pictureService.savePicture(product.getId(), picture);
    }

    @Override
    @Transactional
    public long getPagesCount(String[] categories) {
        if (categories == null)
            return productDAO.getProductsCount();
        return productDAO.getProductsCount(categories);
    }
    @Override
    @Transactional
    public List<ProductDTO> findTopProduct() {
        List<ProductDTO> productDTOs = productDAO.getTopProducts()
                .stream().map(product -> converter.convertToDTO(product))
                .collect(Collectors.toList());

        return productDTOs;
    }
    @Override
    public List<ProductDTO> convertProductsToProductsDTO(List<Product> products) {
        List<ProductDTO> resultList = new ArrayList<>();
        for (Product product : products) {
            resultList.add(converter.convertToDTO(product));
        }
        return resultList;
    }
    @Override
    public void sendUpdateMessageToJmsServer() {
        sendMessage();
    }
    /**
     * Method checks if top is changed.
     *
     * @return true if is changed and false if not.
     */
    @Override
    @Transactional
    public void updateTopIfItHaveChanged() {
        if (tops.isEmpty()) {
            tops = productDAO.getTopProducts();
            sendMessage();
        } else {
            List<Product> foundProducts = productDAO.getTopProducts();
            for (int i = 0; i < 10; i++) {
                if (tops.get(i).getId() != foundProducts.get(i).getId()) {
                    tops = productDAO.getTopProducts();
                    sendMessage();
                    break;
                }
            }
        }
    }

    private void sendMessage() {
        jmsTemplate.send("advertising.stand", session -> {
            TextMessage msg = session.createTextMessage();
            msg.setText("update");
            return msg;
        });
    }
}
