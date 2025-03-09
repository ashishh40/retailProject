package com.retail.retailProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.retail.retailProject.model.User;
import com.retail.retailProject.repository.UserRepository;


@Service
public class RegisterService {

    @Autowired
    public UserRepository repo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseEntity<String> registerUser(User user) {
            if(repo.findByUsername(user.getUsername()).isPresent()){
                throw new IllegalArgumentException("Username already exists.");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
             repo.save(user);
             return ResponseEntity.ok("User login successful!"); 
    }

}
