package com.retail.retailProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.retailProject.model.Cart;
import com.retail.retailProject.model.Item;
import com.retail.retailProject.repository.ItemRepository;
import com.retail.retailProject.service.UserService;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    public UserService userService;


     @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @PostMapping("/cart/{userId}/{itemId}")
    public ResponseEntity<String> addToCart(@PathVariable int userId,@PathVariable int itemId){
        try {
            return userService.addToCart(userId,itemId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


    @GetMapping("/cart/{userId}")
    public List<Cart>  getCart(@PathVariable int userId){
        return userService.getCart(userId);
    }

    @DeleteMapping("/cart/{userId}/{itemId}")
    public ResponseEntity<String> deleteCart(@PathVariable int userId,@PathVariable int itemId){
        return userService.deleteCart(userId,itemId);
    }


    @GetMapping("/checkout/{userId}")
    public ResponseEntity<String> checkout(@PathVariable int userId){
        return userService.checkout(userId);
    }


    @GetMapping("/category/{category}")
    public List<Item> filterByCategory(@PathVariable String category){
        return userService.filterByCategory(category);
    }

}

// Will handle all the user endpoints on the user page 
// like    adding to cart , deleting from cart 
//         the cart page , checkout page



// POST /user/cart/{itemId}