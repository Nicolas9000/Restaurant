package com.example.api.services;

import java.util.List;

import com.example.api.models.Menu;
import com.example.api.models.Restaurant;

public interface MenuService {

    Menu createMenu(Menu menu);

    List<Menu> getRestorerMenusById(Restaurant restaurant);
}
