package com.example.doggoApp.doggoApp.service.impl;

import com.example.doggoApp.doggoApp.domain.Animal;
import com.example.doggoApp.doggoApp.repository.AnimalRepository;
import com.example.doggoApp.doggoApp.service.AnimalService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public Animal getById(Long Id) {
        Animal animal = animalRepository.findById(Id).get();
        if (animal.getIsDeleted()) {
            throw new NoSuchElementException();
        }
        return animal;
    }

    @Override
    public Animal create(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public Animal update(Long Id, Animal animal) {
        animal.setId(Id);
        return animalRepository.save(animal);
    }

    @Override
    public void delete(Animal animal) {
        animal.setIsDeleted(true);
        animalRepository.save(animal);
    }
}