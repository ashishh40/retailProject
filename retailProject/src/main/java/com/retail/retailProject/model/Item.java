package com.retail.retailProject.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
}


//  THE ITEMS DB IS WHERE ALL THE ITEMS WHICH THE STORE HAS ARE STORED
// # Items Table
// ID    NAME    DESCRIPTION    PRICE    AVAILABLE(maybe)