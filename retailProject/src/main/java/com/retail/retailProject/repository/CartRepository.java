package com.retail.retailProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.retailProject.model.Cart;
import com.retail.retailProject.model.User;

public interface CartRepository extends JpaRepository<Cart,Integer>{

    List<Cart> findByUser(User user); 
    void deleteByUserUserIdAndItemItemId(int userId,int itemId);
}
