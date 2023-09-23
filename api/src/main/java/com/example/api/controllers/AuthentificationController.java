package com.example.api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.models.Role;
import com.example.api.models.User;
import com.example.api.services.AuthenticationService;
import com.example.api.services.JwtService;
import com.example.api.services.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthentificationController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    JwtService jwtService;

    @PostMapping("signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        try {
            authenticationService.register(user);

            return ResponseEntity.ok("User created successful");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("signin")
    public ResponseEntity<Object> signin(@RequestBody User user) {
        log.debug("oui dans la route");
        try {
            User findedUser = authenticationService.login(user);

            if (findedUser == null) {
                throw new Exception("Incorrect email or password");
            }

            boolean isPasswordValid = authenticationService.passwordVerify(user.getPassword(),
                    findedUser.getPassword());

            if (!isPasswordValid) {
                throw new Exception("Email or password not valid");
            }

            String token = jwtService.generateToken(findedUser);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("test")
    public Map<String, String> test() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "test");
        return response;
    }


    @PostMapping("teste")
    public Map<String, String> teste(@RequestBody String abc) {
        Map<String, String> response = new HashMap<>();
        response.put("message", abc);
        return response;
    }
}
