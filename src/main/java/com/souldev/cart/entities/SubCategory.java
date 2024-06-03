package com.souldev.cart.entities;

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
import javax.persistence.ManyToOne;
//import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor

public class SubCategory {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy 
    = "org.hibernate.id.UUIDGenerator")
    @Getter @Setter
    private String id;


    @NotBlank @NotNull
    @Getter @Setter
    private String name;


    @NotBlank @NotNull
    @Getter @Setter
    private String image;

    @ManyToOne(optional = false, cascade 
    = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Category category;
    
}
