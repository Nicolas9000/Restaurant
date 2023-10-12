package com.example.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.api.models.Dish;
import com.example.api.models.Restaurant;

public interface DishRepository extends JpaRepository<Dish, Long> {

    @Query("select d from Dish d where d.restaurant = ?1")
    List<Dish> getRestorerDishsById(Restaurant restaurant);
}
