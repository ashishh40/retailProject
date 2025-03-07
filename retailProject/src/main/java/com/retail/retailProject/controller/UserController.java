package com.retail.retailProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.retailProject.service.UserService;

@RestController
@RequestMapping("/api/cart")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/{userId}/{itemId}")
    public ResponseEntity<String> addToCart(@PathVariable int userId,@PathVariable int itemId){
        try {
            return userService.addToCart(userId,itemId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}



// Will handle all the user endpoints on the user page 
// like    adding to cart , deleting from cart 
//         the cart page , checkout page



// POST /user/cart/{itemId}