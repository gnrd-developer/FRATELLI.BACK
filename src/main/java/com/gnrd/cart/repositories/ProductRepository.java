package com.gnrd.cart.repositories;

import com.gnrd.cart.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    List<Product> 
    findBySubCategory_NameAndIdNot(String category, String id);
    List<Product> findBySubCategory_Name(String category);
    List<Product> findByNameContainingIgnoreCase(String name);
}


/*
lo saque de la linea abajo de la 9  List<Product> findFirst4ByOrderByPriceAsc();
*/