package com.github.lynxchina.babypi.controller.model;

/**
 * @author chris
 * @version 1/8/16-3:57 PM
 */
public class Account {

    public String email;
    public String password;
    public String role;

    public Account(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

}
