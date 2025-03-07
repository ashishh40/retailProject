package com.retail.retailProject.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "username",unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean isBlocked;
    private int countOfLogin;
    

}


// ALL THE USER DATA WILL BE STORED HERE

// # Users Table
// ID   USERNAME    PASSWORD    BLOCKSTATUS    CountForNumberOfTries
