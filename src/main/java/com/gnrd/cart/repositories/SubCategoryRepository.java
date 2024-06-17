package com.gnrd.cart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnrd.cart.entities.SubCategory;

public interface SubCategoryRepository extends 
JpaRepository<SubCategory, String> {
    List<SubCategory> findByCategory_Name(String categoryName);
    
}
