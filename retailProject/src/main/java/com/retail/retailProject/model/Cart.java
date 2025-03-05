package com.retail.retailProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int itemId;
}


// THE ITEMS WHICH ARE ADDED TO THE CART WILL BE STORED IN CART DB
// # Cart Table
// ID   USER_ID   ITEM_ID   QUANTITY(maybe)