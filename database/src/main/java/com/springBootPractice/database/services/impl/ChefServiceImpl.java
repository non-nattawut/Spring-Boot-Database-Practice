package com.springBootPractice.database.services.impl;

import com.springBootPractice.database.domain.entities.ChefEntity;
import com.springBootPractice.database.repositories.ChefRepository;
import com.springBootPractice.database.services.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ChefServiceImpl implements ChefService {

    @Autowired
    ChefRepository chefRepository;

    @Override
    public ChefEntity save(ChefEntity chefEntity) {
        return chefRepository.save(chefEntity);
    }

    @Override
    public List<ChefEntity> findAll() {
        return StreamSupport.stream(chefRepository
                        .findAll()
                        .spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ChefEntity> findOne(Long id) {
        return chefRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return chefRepository.existsById(id);
    }

    @Override
    public ChefEntity partialUpdate(Long id, ChefEntity chefEntity) {
        chefEntity.setId(id);

        return chefRepository.findById(id).map(existingChef -> {
            Optional.ofNullable((chefEntity.getName())).ifPresent(existingChef::setName);
            Optional.ofNullable((chefEntity.getAge())).ifPresent(existingChef::setAge);
            return chefRepository.save(existingChef);
        }).orElseThrow(() -> new RuntimeException("Chef not exist"));
    }

    @Override
    public void delete(Long id) {
        chefRepository.deleteById(id);
    }
}
