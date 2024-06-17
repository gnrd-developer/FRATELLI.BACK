package com.gnrd.cart.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Getter @Setter
    private String id;


    @NotBlank @NotNull
    @Getter @Setter
    private String name;

    
    @NotNull @DecimalMin(value = "0.1")
    @Getter @Setter
    private Double price;


    @NotBlank @NotNull
    @Lob
    @Getter @Setter
    private String description;


    @Getter @Setter
    @ManyToOne(optional = false, cascade 
    = CascadeType.DETACH, fetch = FetchType.EAGER)
    private SubCategory subCategory;


    @NotBlank @NotNull
    @Getter @Setter
    private String image;

    
    @Getter @Setter
    private String pdf;
}

//@NotNull @DecimalMin(value = "0.1")
//@Getter @Setter
//private Double price;
