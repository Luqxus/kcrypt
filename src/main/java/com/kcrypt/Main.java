package com.kcrypt;

import java.util.Map;
import java.util.Set;

import com.kcrypt.service.AuthService;
import com.kcrypt.service.IPasswordService;
import com.kcrypt.service.PasswordService;

public class Main {
    public static void main(String[] args) {
        AuthService authService = new AuthService();
        IPasswordService passwordService = new PasswordService();

        authService.createUser("luqus@gmail.com", "12345678");

        String passwordHash = authService.getHashedPassword("luqus@gmail.com");

        passwordService.save(passwordHash, "slack", "454545");

        Set<Map<String, Map<String, String>>> savedPasswords = passwordService.getPasswords();

        System.out.println(savedPasswords);

    }
}