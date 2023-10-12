package com.example.api.services;


import com.example.api.models.Restorer;

public interface RestorerService {
    Restorer restorerRegister(Restorer restorer);

    Restorer getRestorerByUserId(Long userId);
}
