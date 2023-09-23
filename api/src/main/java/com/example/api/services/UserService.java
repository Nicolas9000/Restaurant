package com.example.api.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.api.models.User;

public interface UserService {

    UserDetailsService userDetailsService();
    // User login(String email);
}
