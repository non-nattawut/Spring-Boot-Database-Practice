package com.springBootPractice.database.mappers.impl;

import com.springBootPractice.database.domain.dto.FoodDto;
import com.springBootPractice.database.domain.entities.FoodEntity;
import com.springBootPractice.database.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FoodMapperImpl implements Mapper<FoodEntity, FoodDto> {

    @Autowired
    ModelMapper modelMapper;

    @Override
    public FoodEntity mapToEntity(FoodDto foodDto) {
        return modelMapper.map(foodDto, FoodEntity.class);
    }

    @Override
    public FoodDto mapToDto(FoodEntity foodEntity) {
        return modelMapper.map(foodEntity, FoodDto.class);
    }
}
