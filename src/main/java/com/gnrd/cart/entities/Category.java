package com.gnrd.cart.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = 
    "org.hibernate.id.UUIDGenerator")
    @Getter @Setter
    private String id;


    @NotBlank @NotNull
    @Getter @Setter
    private String name;


    @NotBlank @NotNull
    @Getter @Setter
    private String image;

    
}