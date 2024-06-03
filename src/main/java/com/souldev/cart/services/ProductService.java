package com.souldev.cart.services;


import com.souldev.cart.entities.Product;
import com.souldev.cart.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class ProductService implements IProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveProduct(Product product) {
        this.productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public List<Product> getProductByName(String termino){
        return this.productRepository.findByNameContainingIgnoreCase(termino);
    }

    public List<Product> getAllProductsBySubCategoryName
    (String name){
        return this.productRepository.findBySubCategory_Name
        (name);
    }

    public List<Product> getRelatedProducts
    (String category, String id) {
        List<Product> productList = 
        this.productRepository.findBySubCategory_NameAndIdNot
        (category, id);
        List<Product> randomProducts = new ArrayList<>();
        Random random = new Random();
        System.out.println(productList.size());
        for (int i = 0; i < 2; i++) {
            int randomIndex = random.nextInt
            (productList.size());
            randomProducts.add(productList.get(randomIndex));
            productList.remove(randomIndex);
        }
        return randomProducts;
    }

    public Optional<Product> getProductById(String id) {
        return this.productRepository.findById(id);
    }

    /*public List<Product> getBestPriceProducts() {
        return this.productRepository.findFirst4ByOrderByPriceAsc();
    }*/

    @Override
    public Product convertJsonToProduct(String stringProduct) 
    throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = objectMapper.readValue
        (stringProduct, Product.class);
        return product;
    }
}
