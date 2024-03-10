package com.springBootPractice.database.controller;

import com.springBootPractice.database.domain.dto.ChefDto;
import com.springBootPractice.database.domain.entities.ChefEntity;
import com.springBootPractice.database.mappers.Mapper;
import com.springBootPractice.database.services.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ChefController {

    @Autowired
    private ChefService chefService;

    @Autowired
    private Mapper<ChefEntity, ChefDto> chefMapper;

    @PostMapping(path = "/chefs")
    public ResponseEntity<ChefDto> createChef(@RequestBody ChefDto chefDto){
        ChefEntity chefEntity = chefMapper.mapToEntity(chefDto);
        chefEntity.setId(null);
        ChefEntity savedChefEntity = chefService.save(chefEntity);
        return new ResponseEntity<>(chefMapper.mapToDto(savedChefEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/chefs/{id}")
    public ResponseEntity<ChefDto> findOne(@PathVariable Long id){
        Optional<ChefEntity> foundChefEntity = chefService.findOne(id);
        return foundChefEntity.map(chefEntity -> {
            ChefDto chefDto = chefMapper.mapToDto(chefEntity);
            return new ResponseEntity<>(chefDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/chefs")
    public List<ChefDto> listChef(){
        List<ChefEntity> chefEntityList = chefService.findAll();

        return chefEntityList.stream()
                .map(chefMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping(path = "chefs/{id}")
    public ResponseEntity<ChefDto> fullUpdateChef(@PathVariable Long id, @RequestBody ChefDto chefDto){
        if(!chefService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        chefDto.setId(id);
        ChefEntity chefEntity = chefMapper.mapToEntity(chefDto);
        ChefEntity savedChefEntity = chefService.save(chefEntity);
        return new ResponseEntity<>(chefMapper.mapToDto(savedChefEntity), HttpStatus.OK);
    }

    @PatchMapping(path = "chefs/{id}")
    public ResponseEntity<ChefDto> partialUpdateChef(@PathVariable Long id, @RequestBody ChefDto chefDto){
        if(!chefService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ChefEntity chefEntity = chefMapper.mapToEntity(chefDto);
        ChefEntity savedChefEntity = chefService.partialUpdate(id, chefEntity);
        return new ResponseEntity<>(chefMapper.mapToDto(savedChefEntity), HttpStatus.OK);
    }

    @DeleteMapping(path = "/chefs/{id}")
    public ResponseEntity<HttpStatus> deleteChef(@PathVariable Long id){
        chefService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
