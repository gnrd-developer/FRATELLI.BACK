package com.gnrd.cart.services;
import com.fasterxml.jackson.core.JsonProcessingException;/*Esta excepción se 
lanza cuando hay un error al procesar datos JSON. */
import com.fasterxml.jackson.databind.ObjectMapper;/*Esta clase 
se utiliza para convertir objetos Java a JSON y viceversa. */
import com.gnrd.cart.entities.SubCategory;
import com.gnrd.cart.repositories.SubCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional/*Esta anotación indica que los métodos de esta clase se ejecutan dentro 
de una transacción de base de datos. Una transacción 
garantiza que un conjunto de operaciones en la base de datos sea exitoso o falle por completo.*/
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
