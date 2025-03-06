package com.retail.retailProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.retailProject.model.User;
import com.retail.retailProject.service.RegisterService;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    public RegisterService registerService;

    @PostMapping("/api/register")
    public void registerUser(@RequestBody User user){
        registerService.registerUser(user);
    }
}



// Will handle the registration of a user