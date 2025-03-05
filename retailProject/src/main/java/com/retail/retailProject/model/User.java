package com.retail.retailProject.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String password;
    private boolean blockStatus;
    private int countofLogin;
}


// ALL THE USER DATA WILL BE STORED HERE

// # Users Table
// ID   USERNAME    PASSWORD    BLOCKSTATUS    CountForNumberOfTries
