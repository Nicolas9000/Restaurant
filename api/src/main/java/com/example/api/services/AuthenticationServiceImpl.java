package com.example.api.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.models.User;
import com.example.api.repositories.UserRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public User login(User user) {

        return userRepository.findByEmail(user.getEmail());
    }

    public String passwordHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean passwordVerify(String password, String userPassword) {
        return BCrypt.checkpw(password, userPassword);
    }
}
