package com.retail.retailProject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.retail.retailProject.model.User;
import com.retail.retailProject.repository.UserRepository;

@Service
public class RegisterService {

    private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);

    @Autowired
    public UserRepository repo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseEntity<String> registerUser(User user) {
        logger.info("Received registration request for username: {}", user.getUsername());
            if(repo.findByUsername(user.getUsername()).isPresent()){
                logger.warn("Registration failed: Username '{}' already exists", user.getUsername());
                return ResponseEntity.badRequest().body("Username already exists.");
            }

             user.setPassword(passwordEncoder.encode(user.getPassword()));
             repo.save(user);
             logger.info("User '{}' registration was successful", user.getUsername());
             return ResponseEntity.ok("User registered successful!"); 
    }

}
