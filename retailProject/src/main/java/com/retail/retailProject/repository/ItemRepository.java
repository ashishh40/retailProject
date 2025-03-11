package com.retail.retailProject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.retailProject.model.Item;

public interface ItemRepository extends JpaRepository<Item,Integer>{
        Optional<Item> findById(int itemId);

        List<Item> findByCategory(String category);

}
