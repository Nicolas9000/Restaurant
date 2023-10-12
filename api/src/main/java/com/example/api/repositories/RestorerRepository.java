package com.example.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.models.Restorer;

public interface RestorerRepository extends JpaRepository<Restorer, Long> {

    Restorer getRestorerByUserId(Long userId);
}
