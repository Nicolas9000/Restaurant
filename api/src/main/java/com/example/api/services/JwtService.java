package com.example.api.services;


import org.springframework.security.core.userdetails.UserDetails;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.api.models.User;


public interface JwtService {
    String generateToken(User user);
    
    DecodedJWT decodeJwt(String jwt);

    boolean isTokenValid(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);
}