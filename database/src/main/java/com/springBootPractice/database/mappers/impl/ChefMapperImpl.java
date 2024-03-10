package com.springBootPractice.database.mappers.impl;

import com.springBootPractice.database.domain.dto.ChefDto;
import com.springBootPractice.database.domain.entities.ChefEntity;
import com.springBootPractice.database.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChefMapperImpl implements Mapper<ChefEntity, ChefDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ChefEntity mapToEntity(ChefDto chefDto) {
        return modelMapper.map(chefDto, ChefEntity.class);
    }

    @Override
    public ChefDto mapToDto(ChefEntity chefEntity) {
        return modelMapper.map(chefEntity, ChefDto.class);
    }
}
