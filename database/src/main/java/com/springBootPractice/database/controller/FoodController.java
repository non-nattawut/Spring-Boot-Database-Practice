package com.springBootPractice.database.controller;

import com.springBootPractice.database.domain.dto.FoodDto;
import com.springBootPractice.database.domain.entities.ChefEntity;
import com.springBootPractice.database.domain.entities.FoodEntity;
import com.springBootPractice.database.mappers.Mapper;
import com.springBootPractice.database.repositories.ChefRepository;
import com.springBootPractice.database.services.ChefService;
import com.springBootPractice.database.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class FoodController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private ChefService chefService;
    @Autowired
    private Mapper<FoodEntity, FoodDto> foodMapper;

    @PostMapping(path = "/foods")
    public ResponseEntity<FoodDto> createFood(@RequestBody FoodDto foodDto) {
//        System.out.println(foodDto);
        FoodEntity foodEntity = foodMapper.mapToEntity(foodDto);
        foodEntity.setId(null);
        FoodEntity savedFoodEntity = foodService.save(foodEntity);
        return new ResponseEntity<>(foodMapper.mapToDto(savedFoodEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/foods/{id}")
    public ResponseEntity<FoodDto> findOne(@PathVariable Long id) {
        Optional<FoodEntity> foundFoodEntity = foodService.findOne(id);
        return foundFoodEntity.map(foodEntity -> {
            FoodDto foodDto = foodMapper.mapToDto(foodEntity);
            return new ResponseEntity<>(foodDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/foods")
    public List<FoodDto> listFood() {
        List<FoodEntity> foodEntityList = foodService.findAll();

        return foodEntityList.stream()
                .map(foodMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping(path = "foods/{id}")
    public ResponseEntity<FoodDto> fullUpdateFood(@PathVariable Long id, @RequestBody FoodDto foodDto) {
//        System.out.println(foodDto);
        if (!foodService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        foodDto.setId(id);
        FoodEntity foodEntity = foodMapper.mapToEntity(foodDto);
        FoodEntity savedFoodEntity = foodService.save(foodEntity);
        return new ResponseEntity<>(foodMapper.mapToDto(savedFoodEntity), HttpStatus.OK);
    }

    @PatchMapping(path = "foods/{id}")
    public ResponseEntity<FoodDto> partialUpdateFood(@PathVariable Long id, @RequestBody FoodDto foodDto) {
        if (!foodService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        FoodEntity foodEntity = foodMapper.mapToEntity(foodDto);
        ChefEntity chefEntity = foodEntity.getChef();
        if(chefEntity != null){
            ChefEntity savedChefEntity = chefService.partialUpdate(chefEntity.getId(), chefEntity);
            foodEntity.setChef(savedChefEntity);
        }
        FoodEntity savedFoodEntity = foodService.partialUpdate(id, foodEntity);
        return new ResponseEntity<>(foodMapper.mapToDto(savedFoodEntity), HttpStatus.OK);
    }

    @DeleteMapping(path = "/foods/{id}")
    public ResponseEntity<HttpStatus> deleteFood(@PathVariable Long id) {
        foodService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
