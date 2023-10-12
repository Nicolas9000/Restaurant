package com.example.api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.models.Restorer;
import com.example.api.models.User;
import com.example.api.response.ResponseHandler;
import com.example.api.services.AuthenticationService;
import com.example.api.services.JwtService;
import com.example.api.services.RestorerService;

@RestController
@RequestMapping("/api/auth")
public class AuthentificationController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    JwtService jwtService;

    @Autowired
    RestorerService restorerService;

    @PostMapping("signup")
    public ResponseEntity<Object> signup(@RequestBody User user) {
        try {
            user.setPassword(authenticationService.passwordHash(user.getPassword()));

            User registeredUser = authenticationService.register(user);

            if (registeredUser.getRole().name() == "ROLE_RESTORER") {
                Restorer restorer = new Restorer();
                restorer.setEmail(registeredUser.getEmail());
                restorer.setUser(registeredUser);
                restorerService.restorerRegister(restorer);
            }

            return ResponseHandler.responseBuilder("User created successfull", HttpStatus.OK, null);

        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("signin")
    public ResponseEntity<Object> signin(@RequestBody User user) {

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

            return ResponseHandler.responseBuilder("Authentication successfull", HttpStatus.OK, response);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
