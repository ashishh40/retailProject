package com.retail.retailProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.retailProject.model.Cart;

public interface CartRepository extends JpaRepository<Cart,Integer>{
    

}
