package com.gnrd.cart.controllers;

import com.gnrd.cart.entities.Message;
//import com.gnrd.cart.entities.Product;
import com.gnrd.cart.entities.SubCategory;
import com.gnrd.cart.services.SubCategoryService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/subCategory")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SubCategoryController {

  @Autowired
  private SubCategoryService subCategoryService;

  @GetMapping(value = "/{categoryname}")
  public ResponseEntity<Object> getSubCategories(@PathVariable String categoryname) {
    return new ResponseEntity<>(this.subCategoryService.getSubCategoriesByCategory(categoryname), HttpStatus.OK);
  }

  /*-------------------------------------------------------------- */

  @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Message> createSubCategory(@Valid @RequestPart("subCategory") String stringSubCategory,
            BindingResult bindingResult) {

        try {
            SubCategory subCategory = subCategoryService.convertJsonToSubCategory(stringSubCategory);

            this.subCategoryService.saveSubCategory(subCategory);

            return new ResponseEntity<>(new Message("Actualizado correctamente"), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(new Message("Revise los campos"),

                    HttpStatus.BAD_REQUEST);
        }

    }

  /*-------------------------------------------------------------------------- */

  @GetMapping()
  public ResponseEntity<Object> getAllSubCategories() {
    return new ResponseEntity<>(this.subCategoryService
        .findAll(), HttpStatus.OK);
  }

}
