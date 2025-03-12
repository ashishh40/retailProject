package com.retail.retailProject.service;
import java.util.Optional;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);

    @Autowired
    public UserRepository userRepo;

    @Autowired
    public CartRepository cartRepo;

    @Autowired
    public ItemRepository itemRepo;

    public ResponseEntity<String> addToCart(int userId,int itemId ){
        logger.info("Received add to cart request for userId: {} and itemId: {}", userId, itemId);
        
        Optional<User> userOptional=userRepo.findById(userId);
        Optional<Item> itemOptional=itemRepo.findById(itemId);

        if(!userOptional.isPresent() || !itemOptional.isPresent()){
            logger.warn("Either User or Item not found");
            return ResponseEntity.badRequest().body("Either User or Item not found!");
        }

        User user=userOptional.get();
        Item item = itemOptional.get();

        if(user.isBlocked()){
            logger.warn("User is blocked");
            return ResponseEntity.badRequest().body("User is blocked!");
        }

        Optional<Cart> existingCartItem = cartRepo.findByUserAndItem(user, item);
        if (existingCartItem.isPresent()) {
            logger.warn("Item is already in the cart");
            return ResponseEntity.badRequest().body("Item is already in the cart.");
        }

        Cart cart=new Cart();

        cart.setItem(item);
        cart.setUser(user);

        cartRepo.save(cart);
        logger.info("Added to cart successful");
        return ResponseEntity.ok("Added to Cart successful!");
    }

    public List<Cart> getCart(int userId){
        logger.info("Received get cart request for userId: {}", userId);
        Optional<User> userOptional=userRepo.findById(userId);

        if(userOptional.isEmpty()){
            logger.warn("User not found");
            throw new IllegalArgumentException("User not found!");
        }

        logger.info("Returning cart for userId: {}", userId);
        return cartRepo.findByUser(userOptional.get());
    }

    @Transactional
    public ResponseEntity<String> deleteCart(int userId,int itemId){
        logger.info("Received delete cart request for userId: {} and itemId: {}", userId, itemId);
        cartRepo.deleteByUserUserIdAndItemItemId(userId,itemId);

        logger.info("Deleted from cart successful");
        return ResponseEntity.ok("deleted from cart successful!");
    }

    @Transactional
    public ResponseEntity<String> checkout(int userId){
        logger.info("Received checkout request for userId: {}", userId);
        User user=userRepo.findById(userId).get();

        List<Cart> cartList=cartRepo.findByUser(user);

        Double totalAmount = cartList.stream().mapToDouble(cart-> cart.getItem().getPrice()).sum();

        cartRepo.deleteAll(cartList);

        logger.info("Checkout successful");

        return ResponseEntity.ok( totalAmount.toString());
    }

    public List<Item> filterByCategory(String category) {
        logger.info("Received filter by category request for category: {}", category);
        return itemRepo.findByCategory(category);
    }
}
