package com.example.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.models.Menu;
import com.example.api.models.Restaurant;
import com.example.api.repositories.MenuRepository;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Override
    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public List<Menu> getRestorerMenusById(Restaurant restaurant) {
        return menuRepository.getRestorerMenusById(restaurant);
    }

}
