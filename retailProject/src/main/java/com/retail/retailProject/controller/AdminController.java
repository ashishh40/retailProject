package com.retail.retailProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.retailProject.model.User;
import com.retail.retailProject.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    public AdminService adminService;
    
    @GetMapping("/blocked")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getBlockedUsers() {
        List<User> blockedUsers = adminService.getBlockedUsers();
        return ResponseEntity.ok(blockedUsers);
    }

    @PutMapping("/unblock/{userId}")
    public ResponseEntity<String> unblockUser(@PathVariable int userId){
        adminService.unblockUser(userId);
        return ResponseEntity.ok("User unblocked successfully!");
    }

}



// Will handle the admin operation like unblocking a user 
// will see if the admin has to add the items or not not sure where the items will be added from 