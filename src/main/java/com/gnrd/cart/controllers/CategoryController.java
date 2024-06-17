package com.gnrd.cart.controllers;

import com.gnrd.cart.entities.Category;
import com.gnrd.cart.entities.Message;
import com.gnrd.cart.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
/*  Esta importación es para la anotación Autowired de Spring,
    que se utiliza para la inyección automática de dependencias en tus clases. 
*/

import org.springframework.http.HttpStatus;
/* se utilizan para manejar las respuestas HTTP en tus controladores. */

import org.springframework.http.ResponseEntity;
/* se utilizan para manejar las respuestas HTTP en tus controladores. */

import org.springframework.web.bind.annotation.*;
/* Esta importación es para todas las anotaciones
del controlador web de Spring como @RestController, 
@RequestMapping, @GetMapping, etc., que se utilizan para 
mapear las solicitudes web a tus métodos de controlador.. */

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<Object> getAll(){
        return new ResponseEntity<>(this
        .categoryService.getAllCategories(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Message> create
    (@RequestBody Category category){
        this.categoryService.createCategory(category);
        return new ResponseEntity<>(new Message
        ("Creado"),HttpStatus.OK);
    }
    
}
