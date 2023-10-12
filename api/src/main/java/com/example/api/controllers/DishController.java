package com.example.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.api.models.Dish;
import com.example.api.models.Restaurant;
import com.example.api.models.Restorer;
import com.example.api.response.ResponseHandler;
import com.example.api.services.DishService;
import com.example.api.services.JwtService;
import com.example.api.services.RestaurantService;
import com.example.api.services.RestorerService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/dish")
public class DishController {

    @Autowired
    DishService dishService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    JwtService jwtService;

    @Autowired
    RestorerService restorerService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRestaurantDish(@PathVariable Long id) {
        try {

            Restaurant restaurant = new Restaurant();
            restaurant.setId(id);

            List<Dish> dishs = dishService.getRestorerDishsById(restaurant);

            return ResponseHandler.responseBuilder("Restaurant dish finded successfull", HttpStatus.OK, dishs);

        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createDish(@RequestBody Dish dish, HttpServletRequest request) {
        try {
            String token = jwtService.getAuthorizationToken(request);
            DecodedJWT decodedToken = jwtService.decodeJwt(token);
            Long userId = decodedToken.getClaim("id").asLong();
            Restorer restorer = restorerService.getRestorerByUserId(userId);
            Restaurant restaurant = restaurantService.getRestaurantByRestorerId(restorer.getId());

            dish.setRestaurant(restaurant);

            Dish savedDish = dishService.createDish(dish);
            return ResponseHandler.responseBuilder("Dish created successfull", HttpStatus.OK, savedDish);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);

        }
    }
}
