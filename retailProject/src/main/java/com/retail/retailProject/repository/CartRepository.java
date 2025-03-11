package com.retail.retailProject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.retailProject.model.Cart;
import com.retail.retailProject.model.Item;
import com.retail.retailProject.model.User;

public interface CartRepository extends JpaRepository<Cart,Integer>{

    List<Cart> findByUser(User user); 
    void deleteByUserUserIdAndItemItemId(int userId,int itemId);

    Optional<Cart> findByUserAndItem(User user, Item item);
}
