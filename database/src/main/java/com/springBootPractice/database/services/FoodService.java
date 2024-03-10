package com.springBootPractice.database.services;

import com.springBootPractice.database.domain.entities.FoodEntity;

import java.util.List;
import java.util.Optional;

public interface FoodService {

    FoodEntity save(FoodEntity foodEntity);

    List<FoodEntity> findAll();

    Optional<FoodEntity> findOne(Long id);

    boolean isExists(Long id);

    FoodEntity partialUpdate(Long id, FoodEntity foodEntity);

    void delete(Long id);
}
