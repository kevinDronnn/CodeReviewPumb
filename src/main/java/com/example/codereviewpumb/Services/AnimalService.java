package com.example.codereviewpumb.Services;

import com.example.codereviewpumb.Entity.AnimalEntity;
import com.example.codereviewpumb.Repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {
    @Autowired
    AnimalRepository animalRepository;

    public List<AnimalEntity> getAnimalsByParams(String type, String sex, Byte category, String column) {

        return animalRepository.getAnimalByFilter(type, sex, category, column);
    }
}
