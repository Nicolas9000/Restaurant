package com.example.api.services;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.api.models.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {
    @Value("${token.secret.key}")
    String jwtSecretKey;

    @Value("${token.expirationms}")
    Long jwtExpirationMs;

    @Override
    public String generateToken(User user) {
        try {
            String token = JWT.create()
                    .withClaim("id", user.getId())
                    .withClaim("email", user.getEmail())
                    .withClaim("role", user.getRole().name())
                    .withIssuedAt(new Date(System.currentTimeMillis()))
                    .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpirationMs))
                    .sign(getJwtKey());
            return token;
        } catch (JWTCreationException exception) {
            throw exception;
        }
    }

    @Override
    public DecodedJWT decodeJwt(String jwt) {
        try {
            JWTVerifier verifier = JWT.require(getJwtKey()).build();

            DecodedJWT decodedJWT = verifier.verify(jwt);

            return decodedJWT;
        } catch (JWTVerificationException exception) {

            throw exception;
        }
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String userEmail = decodeJwt(token).getClaim("email").asString();
        return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        DecodedJWT jwt = decodeJwt(token);

        return jwt.getExpiresAt().before(new Date());
    }

    private Algorithm getJwtKey() {
        return Algorithm.HMAC256(jwtSecretKey);
    }

}
