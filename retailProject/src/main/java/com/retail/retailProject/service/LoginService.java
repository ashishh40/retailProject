package com.retail.retailProject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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


    public String loginUser(String username, String password) {
        Optional<User> userOptional = repo.findByUsername(username);

        if (userOptional.isEmpty()) {
            return "User not found!";
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Invalid credentials!";
        }

        System.out.println("login succesfull");
        return "Login successful!";
    }
}
