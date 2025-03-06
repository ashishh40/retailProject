package com.retail.retailProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.retailProject.model.User;
import com.retail.retailProject.service.LoginService;


@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    public LoginService loginService;

    @PostMapping("/login/user")
    public void loginUser(@RequestBody User user){

        loginService.loginUser(user.getUsername(),user.getPassword());
    }
}



// Will handle the login for user as well as the admin