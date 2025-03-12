package com.retail.retailProject.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.retailProject.model.User;
import com.retail.retailProject.repository.UserRepository;


@Service
public class AdminService {

    private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);


    @Autowired
    public UserRepository userRepo;

    public List<User> getBlockedUsers() {
        logger.info("Fetching all blocked users");
        return userRepo.findByIsBlockedTrue();
    }

    public void unblockUser(int userId) {
        logger.info("Unblocking user with userId: {}", userId);
        Optional<User> userOptional=userRepo.findById(userId);
        if(userOptional.isPresent()){
            logger.info("User found, unblocking user");
            User user=userOptional.get();
            user.setBlocked(false);
            logger.info("User '{}' unblocked successfully", user.getUsername());
            userRepo.save(user);
        }
    }

}
