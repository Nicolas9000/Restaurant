package com.example.api.models;

import java.time.LocalDateTime;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class UserEntityListener {

    @PrePersist
    public void beforeCreate(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void beforeUpdate(User user) {
        user.setUpdatedAt(LocalDateTime.now());
    }
}
