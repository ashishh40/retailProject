package com.retail.retailProject.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.retail.retailProject.model.Cart;
import com.retail.retailProject.model.Item;
import com.retail.retailProject.model.User;

import com.retail.retailProject.repository.CartRepository;
import com.retail.retailProject.repository.ItemRepository;
import com.retail.retailProject.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepo;

    @Autowired
    public CartRepository cartRepo;

    @Autowired
    public ItemRepository itemRepo;

    public ResponseEntity<String> addToCart(int userId,int itemId ){
        
        Optional<User> user=userRepo.findById(userId);
        Optional<Item> item=itemRepo.findById(itemId);

        if(!user.isPresent() || !item.isPresent()){
            return ResponseEntity.badRequest().body("Either User or Item not found!");
        }

        Cart cart=new Cart();
        cart.setItemId(itemId);
        cart.setUserId(userId);

        cartRepo.save(cart);
        return ResponseEntity.ok("Added to Cart successful!");
    }
}
