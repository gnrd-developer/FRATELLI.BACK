package com.souldev.cart.services;
import java.util.List;
import java.util.Optional;

import com.souldev.cart.entities.Product;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IProductService {
	void saveProduct(Product product);
	List<Product> getAllProducts();
	List<Product> getRelatedProducts(String category, 
	String id);
	Optional<Product> getProductById(String id);
	Product convertJsonToProduct(String stringProduct) 
	throws JsonProcessingException;
	List<Product> getProductByName(String name);
}