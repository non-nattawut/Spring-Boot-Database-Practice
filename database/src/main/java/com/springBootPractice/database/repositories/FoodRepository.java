package com.springBootPractice.database.repositories;

import com.springBootPractice.database.domain.entities.FoodEntity;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<FoodEntity, Long> {

}
