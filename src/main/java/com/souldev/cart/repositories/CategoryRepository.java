package com.souldev.cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.souldev.cart.entities.Category;

public interface CategoryRepository extends 
JpaRepository<Category, String>{
    
}
