package com.example.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.models.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
 
    Restaurant getRestaurantByRestorerId(Long restorerId);
}
