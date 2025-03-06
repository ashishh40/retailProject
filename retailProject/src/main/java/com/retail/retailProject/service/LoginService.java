package com.retail.retailProject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.retail.retailProject.model.User;
import com.retail.retailProject.repository.UserRepository;


@Service
public class LoginService {

    @Autowired
    public UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private int maxLoginAttempt=3;


    public ResponseEntity<String> loginUser(String username, String password) {
        Optional<User> userOptional = repo.findByUsername(username);


        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found!");
        }


        User user = userOptional.get();

        if (user.isBlocked()) {
            return ResponseEntity.status(403).body("Your account is locked due to multiple failed login attempts.");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            
            user.setCountOfLogin(user.getCountOfLogin()+1);
            repo.save(user); 


            if(user.getCountOfLogin()>=maxLoginAttempt){
                user.setBlocked(true);
                repo.save(user);
            }
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");

        }

        // System.out.println("login succesfull");
        user.setCountOfLogin(0);
        repo.save(user); 

        System.out.println(user);
        return ResponseEntity.ok("Login successful!");
    }
}
