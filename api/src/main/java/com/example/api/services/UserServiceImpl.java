package com.example.api.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.api.models.User;
import com.example.api.repositories.UserRepository;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public User login(String email) {
        return userRepository.findByEmail(email);
    }

}
