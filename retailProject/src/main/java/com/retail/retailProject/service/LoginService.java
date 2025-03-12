package com.retail.retailProject.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.retail.retailProject.model.User;
import com.retail.retailProject.repository.UserRepository;


@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);

    @Autowired
    public UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private int maxLoginAttempt=3;


    public ResponseEntity<Map<String, Object>> loginUser(String username, String password) {
        logger.info("Received login request for username: {}", username);
        Optional<User> userOptional = repo.findByUsername(username);


        if (userOptional.isEmpty()) {
            logger.warn("User '{}' not found",username);
            return ResponseEntity.badRequest().body(Map.of("error", "User not found!"));
        }


        User user = userOptional.get();

        if (user.isBlocked()) {
            logger.warn("User '{}' is blocked",username);
            return ResponseEntity.status(403).body(Map.of("error", "Your account is locked due to multiple failed login attempts."));
        }

        if (user.isAdmin()) {
            if (!user.getPassword().equals(password)) {
                logger.warn("Invalid admin credentials for '{}'",username);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid admin credentials!"));
            }
        }
        else if (!passwordEncoder.matches(password, user.getPassword())) {
            logger.warn("Invalid credentials for '{}'",username);
            
            user.setCountOfLogin(user.getCountOfLogin()+1);
            repo.save(user); 


            if(user.getCountOfLogin()>=maxLoginAttempt){
                logger.warn("User '{}' is blocked due to multiple failed login attempts",username);
                user.setBlocked(true);
                repo.save(user);
            }
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials!"));

        }

        user.setCountOfLogin(0);
        repo.save(user); 

        logger.info("User '{}' login successful",username);


        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful!");
        response.put("userId", user.getUserId());
        response.put("isAdmin", user.isAdmin());
        System.out.println(user);
        return ResponseEntity.ok(response);
    }
}
