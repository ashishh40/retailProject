package com.retail.retailProject.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
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

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    private boolean blockStatus;
    private int countofLogin;

    
    public void setId(int id) {
        this.id = id;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setBlockStatus(boolean blockStatus) {
        this.blockStatus = blockStatus;
    }
    public void setCountofLogin(int countofLogin) {
        this.countofLogin = countofLogin;
    }
    public int getId() {
        return id;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public boolean isBlockStatus() {
        return blockStatus;
    }
    public int getCountofLogin() {
        return countofLogin;
    }
}


// ALL THE USER DATA WILL BE STORED HERE

// # Users Table
// ID   USERNAME    PASSWORD    BLOCKSTATUS    CountForNumberOfTries
