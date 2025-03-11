package com.retail.retailProject.service;
import java.util.Optional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.retail.retailProject.model.Cart;
import com.retail.retailProject.model.Item;
import com.retail.retailProject.model.User;

import com.retail.retailProject.repository.CartRepository;
import com.retail.retailProject.repository.ItemRepository;
import com.retail.retailProject.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepo;

    @Autowired
    public CartRepository cartRepo;

    @Autowired
    public ItemRepository itemRepo;

    public ResponseEntity<String> addToCart(int userId,int itemId ){
        
        Optional<User> userOptional=userRepo.findById(userId);
        Optional<Item> itemOptional=itemRepo.findById(itemId);

        if(!userOptional.isPresent() || !itemOptional.isPresent()){
            return ResponseEntity.badRequest().body("Either User or Item not found!");
        }

        User user=userOptional.get();
        Item item = itemOptional.get();

        if(user.isBlocked()){
            return ResponseEntity.badRequest().body("User is blocked!");
        }


        System.out.println("Adding to cart - UserId: " + user.getUserId() + ", ItemId: " + item.getItemId()); // for debugging

        Cart cart=new Cart();
        // cart.setItemId(itemId);
        // cart.setUserId(userId);

        cart.setItem(item);
        cart.setUser(user);

        cartRepo.save(cart);
        return ResponseEntity.ok("Added to Cart successful!");
    }

    public List<Cart> getCart(int userId){
        Optional<User> userOptional=userRepo.findById(userId);

        if(userOptional.isEmpty()){
            throw new IllegalArgumentException("User not found!");
        }

        return cartRepo.findByUser(userOptional.get());
    }

    @Transactional
    public ResponseEntity<String> deleteCart(int userId,int itemId){
        // Optional<User> userOptional=userRepo.findById(userId);
        cartRepo.deleteByUserUserIdAndItemItemId(userId,itemId);
        return ResponseEntity.ok("deleted from cart successful!");
    }

    @Transactional
    public ResponseEntity<String> checkout(int userId){
        User user=userRepo.findById(userId).get();

        List<Cart> cartList=cartRepo.findByUser(user);

        Double totalAmount = cartList.stream().mapToDouble(cart-> cart.getItem().getPrice()).sum();

        return ResponseEntity.ok( totalAmount.toString());
    }
}
