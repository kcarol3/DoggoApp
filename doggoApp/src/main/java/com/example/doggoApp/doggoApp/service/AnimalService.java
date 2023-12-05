package com.example.doggoApp.doggoApp.service;

import com.example.doggoApp.doggoApp.domain.Animal;

import java.util.List;

public interface AnimalService {
    Animal getById(Long Id);

    Animal create(Animal animal);

    Animal update(Long Id, Animal animal);

    List<Animal> getAnimalsByUserId(Long userId);

    void delete(Animal animal);
}
