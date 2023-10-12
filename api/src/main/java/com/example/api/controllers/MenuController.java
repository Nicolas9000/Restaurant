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
import com.example.api.models.Menu;
import com.example.api.models.Restaurant;
import com.example.api.models.Restorer;
import com.example.api.response.ResponseHandler;
import com.example.api.services.JwtService;
import com.example.api.services.MenuService;
import com.example.api.services.RestaurantService;
import com.example.api.services.RestorerService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @Autowired
    JwtService jwtService;

    @Autowired
    RestorerService restorerService;

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMenusByRestaurantId(@PathVariable Long id) {
        try {

            Restaurant restaurant = new Restaurant();
            restaurant.setId(id);

            List<Menu> restorerMenus = menuService.getRestorerMenusById(restaurant);

            return ResponseHandler.responseBuilder("Restorer menus", HttpStatus.OK, restorerMenus);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createMenu(@RequestBody Menu menu, HttpServletRequest request) {
        try {

            String token = jwtService.getAuthorizationToken(request);
            DecodedJWT decodedToken = jwtService.decodeJwt(token);
            Long userId = decodedToken.getClaim("id").asLong();
            Restorer restorer = restorerService.getRestorerByUserId(userId);
            Restaurant restaurant = restaurantService.getRestaurantByRestorerId(restorer.getId());
            menu.setRestaurant(restaurant);
            Menu savedMenu = menuService.createMenu(menu);
            return ResponseHandler.responseBuilder("Menu created successfull", HttpStatus.CREATED, savedMenu);

        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
