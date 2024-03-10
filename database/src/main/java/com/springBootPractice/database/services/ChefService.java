package com.springBootPractice.database.services;

import com.springBootPractice.database.domain.entities.ChefEntity;
import java.util.List;
import java.util.Optional;

public interface ChefService {
    ChefEntity save(ChefEntity chefEntity);

    List<ChefEntity> findAll();

    Optional<ChefEntity> findOne(Long id);

    boolean isExists(Long id);

    ChefEntity partialUpdate(Long id, ChefEntity chefEntity);

    void delete(Long id);
}
