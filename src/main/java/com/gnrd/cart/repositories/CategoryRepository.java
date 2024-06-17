package com.gnrd.cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnrd.cart.entities.Category;

public interface CategoryRepository extends 
JpaRepository<Category, String>{
    
}
