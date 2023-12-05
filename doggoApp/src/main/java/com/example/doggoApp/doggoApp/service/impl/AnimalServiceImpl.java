package com.example.doggoApp.doggoApp.service.impl;

import com.example.doggoApp.doggoApp.domain.Animal;
import com.example.doggoApp.doggoApp.repository.AnimalRepository;
import com.example.doggoApp.doggoApp.service.AnimalService;
import com.example.doggoApp.doggoApp.service.ImageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
    private final ImageService imageService;

    public AnimalServiceImpl(AnimalRepository animalRepository, ImageService imageService) {
        this.animalRepository = animalRepository;
        this.imageService = imageService;
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
    public List<Animal> getAnimalsByUserId(Long userId) {
        return animalRepository.findAnimalsByUserId(userId);
    }

    @Override
    public void delete(Animal animal) {
        animal.setIsDeleted(true);
        animalRepository.save(animal);
        imageService.deleteImage(animal.getImage().getId());
    }
}
