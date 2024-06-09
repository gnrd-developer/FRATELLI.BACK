package com.souldev.cart.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.souldev.cart.entities.SubCategory;
import com.souldev.cart.repositories.SubCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
public class SubCategoryService {
    
    private final SubCategoryRepository subCategoryRepository;


    @Autowired
    public SubCategoryService(SubCategoryRepository 
    subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    public List<SubCategory> getSubCategoriesByCategory
    (String name){
        return this.subCategoryRepository
        .findByCategory_Name(name);
    }

    public void saveSubCategory(SubCategory subCategory){
        this.subCategoryRepository.save(subCategory);
    }

    public List<SubCategory> findAll(){
        return this.subCategoryRepository.findAll();
    }

    
    public SubCategory convertJsonToSubCategory(String stringSubCategory) 
    throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        SubCategory subCategory = objectMapper.readValue
        (stringSubCategory, SubCategory.class);
        return subCategory;
    }
}
