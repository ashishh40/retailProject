package com.retail.retailProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.retailProject.model.User;
import com.retail.retailProject.repository.UserRepository;


@Service
public class AdminService {

    @Autowired
    public UserRepository userRepo;

    public List<User> getBlockedUsers() {
        return userRepo.findByIsBlockedTrue();
    }

    public void unblockUser(int userId) {
        Optional<User> userOptional=userRepo.findById(userId);
        if(userOptional.isPresent()){
            User user=userOptional.get();
            user.setBlocked(false);
            userRepo.save(user);
        }
    }

}
