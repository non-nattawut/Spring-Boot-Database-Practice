package com.springBootPractice.database.services.impl;

import com.springBootPractice.database.domain.entities.ChefEntity;
import com.springBootPractice.database.domain.entities.FoodEntity;
import com.springBootPractice.database.repositories.FoodRepository;
import com.springBootPractice.database.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodRepository foodRepository;

    @Override
    public FoodEntity save(FoodEntity foodEntity) {
        return foodRepository.save(foodEntity);
    }

    @Override
    public List<FoodEntity> findAll() {
        return StreamSupport.stream(
                foodRepository
                        .findAll()
                        .spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FoodEntity> findOne(Long id) {
        return foodRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return foodRepository.existsById(id);
    }

    @Override
    public FoodEntity partialUpdate(Long id, FoodEntity foodEntity) {
        foodEntity.setId(id);

        return foodRepository.findById(id).map(existingFood -> { // find by id ก็ map id ใส่ existing food อยู่แล้ว
            Optional.ofNullable((foodEntity.getName())).ifPresent(existingFood::setName);
            Optional.ofNullable((foodEntity.getCalories())).ifPresent(existingFood::setCalories);
            Optional.ofNullable((foodEntity.getChef())).ifPresent(existingFood::setChef);

            return foodRepository.save(existingFood);
        }).orElseThrow(() -> new RuntimeException("Chef not exist"));
    }

    @Override
    public void delete(Long id) {
        foodRepository.deleteById(id);
    }
}
