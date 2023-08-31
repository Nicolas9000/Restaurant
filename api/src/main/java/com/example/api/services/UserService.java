package com.example.api.services;

import com.example.api.models.User;

public interface UserService {
    User register(User user);

    User login(String email);
}
