package com.example.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.api.models.Menu;
import com.example.api.models.Restaurant;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    
    @Query("select m from Menu m where m.restaurant = ?1")
    List<Menu> getRestorerMenusById(Restaurant restaurant);
}
