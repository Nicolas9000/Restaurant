package com.example.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.models.Restaurant;
import com.example.api.repositories.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant getRestaurantByRestorerId(Long restorerId) {
        return restaurantRepository.getRestaurantByRestorerId(restorerId);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, Restaurant restaurant) {
        return restaurantRepository.findById(restaurantId)
                .map(r -> {
                    r.setName(restaurant.getName());
                    r.setEmail(restaurant.getEmail());
                    r.setPhone(restaurant.getPhone());
                    r.setDescription(restaurant.getDescription());
                    r.setPicture(restaurant.getPicture());
                    r.setStreet(restaurant.getStreet());
                    r.setCity(restaurant.getCity());
                    r.setZipcode(restaurant.getZipcode());
                    r.setCountry(restaurant.getCountry());
                    return restaurantRepository.save(r);
                }).orElseThrow(() -> new RuntimeException("Restaurant non trouvé"));
    }

}
