package com.example.api.services;


import com.example.api.models.User;

public interface AuthenticationService {
    User register(User user);   
 
    User login(User user);

    boolean passwordVerify(String password, String userPassword);
}
