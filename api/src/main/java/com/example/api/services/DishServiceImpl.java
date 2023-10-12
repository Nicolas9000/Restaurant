package com.example.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.models.Dish;
import com.example.api.models.Restaurant;
import com.example.api.repositories.DishRepository;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    DishRepository dishRepository;

    @Override
    public Dish createDish(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public List<Dish> getRestorerDishsById(Restaurant restaurant) {
        return dishRepository.getRestorerDishsById(restaurant);
    }
    
}
