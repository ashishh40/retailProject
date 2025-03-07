package com.retail.retailProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // private int userId;
    // private int itemId;

     @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)  
    private Item item;
}


// THE ITEMS WHICH ARE ADDED TO THE CART WILL BE STORED IN CART DB
// # Cart Table
// ID   USER_ID   ITEM_ID   QUANTITY(maybe)