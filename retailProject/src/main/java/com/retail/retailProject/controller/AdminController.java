// package com.retail.retailProject.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.retail.retailProject.model.User;
// import com.retail.retailProject.service.AdminService;

// @RestController
// public class AdminController {

//     @Autowired
//     public AdminService adminService;
    
//     @GetMapping("/admin/blocked")
//     public List<User> getBlockedUsers(){
//         return adminService.getBlockedUsers();
//     }
// }



// // Will handle the admin operation like unblocking a user 
// // will see if the admin has to add the items or not not sure where the items will be added from 