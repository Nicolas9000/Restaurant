package com.example.api.services;


import com.example.api.models.Restaurant;

public interface RestaurantService {
    Restaurant createRestaurant(Restaurant restaurant);

    Restaurant getRestaurantByRestorerId(Long restorerId);

    Restaurant updateRestaurant(Long restaurantId, Restaurant restaurant);
}
