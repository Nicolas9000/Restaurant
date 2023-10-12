package com.example.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.api.models.Restaurant;
import com.example.api.models.Restorer;
import com.example.api.response.ResponseHandler;
import com.example.api.services.JwtService;
import com.example.api.services.RestaurantService;
import com.example.api.services.RestorerService;

import jakarta.servlet.http.HttpServletRequest;
// import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/restaurant")
// @Slf4j
public class RestaurantController {

    @Autowired
    JwtService jwtService;

    @Autowired
    RestorerService restorerService;

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("my_restaurant")
    @PreAuthorize("hasRole('RESTORER')")
    public ResponseEntity<Object> getRestorerRestaurant(HttpServletRequest request) {
        try {
            String token = jwtService.getAuthorizationToken(request);
            DecodedJWT decodedToken = jwtService.decodeJwt(token);
            Long userId = decodedToken.getClaim("id").asLong();
            Restorer restorer = restorerService.getRestorerByUserId(userId);

            Restaurant restaurant = restaurantService.getRestaurantByRestorerId(restorer.getId());

            if (restaurant == null) {
                throw new Exception("Restaurant not found");
            }

            return ResponseHandler.responseBuilder("Restorer restaurant found", HttpStatus.OK, restaurant);

        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('RESTORER')")
    public ResponseEntity<Object> createRestaurant(@RequestBody Restaurant restaurant, HttpServletRequest request) {
        try {
            String token = jwtService.getAuthorizationToken(request);
            DecodedJWT decodedToken = jwtService.decodeJwt(token);
            Long userId = decodedToken.getClaim("id").asLong();
            Restorer restorer = restorerService.getRestorerByUserId(userId);

            if (restorer == null) {
                throw new Exception("Restorer not found");
            }

            restaurant.setRestorer(restorer);
            Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);

            return ResponseHandler.responseBuilder("Restaurant created successfull", HttpStatus.OK, createdRestaurant);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('RESTORER')")
    public ResponseEntity<Object> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        try {

            Restaurant updatedRestaurant = restaurantService.updateRestaurant(id, restaurant);

            return ResponseHandler.responseBuilder("Restaurant updated successfull", HttpStatus.OK, updatedRestaurant);

        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
