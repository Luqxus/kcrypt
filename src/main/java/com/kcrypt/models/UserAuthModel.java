package com.kcrypt.models;


public class UserAuthModel {
    String email;
    String password;

    UserAuthModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}