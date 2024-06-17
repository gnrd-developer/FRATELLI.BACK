package com.gnrd.cart.services;

import com.gnrd.cart.entities.Category;
import com.gnrd.cart.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(){
        return this.categoryRepository.findAll();
    }

    public void createCategory(Category category){
        this.categoryRepository.save(category);
    }
    
}
