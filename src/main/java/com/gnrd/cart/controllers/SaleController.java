package com.gnrd.cart.controllers;

import com.gnrd.cart.entities.Message;
import com.gnrd.cart.entities.Sale;
import com.gnrd.cart.services.EmailService;
import com.gnrd.cart.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    private final SaleService saleService;
    private final EmailService emailService;

    @Autowired
    public SaleController(SaleService saleService, EmailService emailService) {
        this.saleService = saleService;
        this.emailService = emailService;
    }
    
    @GetMapping("/client")
    public ResponseEntity<List<Sale>> getByClient(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String userName = userDetails.getUsername();
        return new ResponseEntity<>(this.saleService.getSalesByClient(userName), HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Message> createSale(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
        String userName = userDetails.getUsername();
        this.emailService.sendListEmail("gnrd.developer@gmail.com", userName);
        this.saleService.createSale(userName);
        return new ResponseEntity<>(new Message("Compra exitosa"), HttpStatus.OK);
    }
}
