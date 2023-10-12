package com.example.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.models.Restorer;
import com.example.api.repositories.RestorerRepository;

@Service
public class RestorerServiceImpl implements RestorerService {

    @Autowired
    RestorerRepository restorerRepository;

    @Override
    public Restorer restorerRegister(Restorer restorer) {
        return restorerRepository.save(restorer);
    }

    @Override
    public Restorer getRestorerByUserId(Long userId) {
        return restorerRepository.getRestorerByUserId(userId);
    }
    
}
