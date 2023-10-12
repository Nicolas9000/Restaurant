package com.example.api.services;

import java.util.List;

import com.example.api.models.Dish;
import com.example.api.models.Restaurant;

public interface DishService {
    Dish createDish(Dish dish);

    List<Dish> getRestorerDishsById(Restaurant restaurant);
}
