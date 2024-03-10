package com.springBootPractice.database.repositories;

import com.springBootPractice.database.domain.entities.ChefEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefRepository extends CrudRepository<ChefEntity, Long> {

}
