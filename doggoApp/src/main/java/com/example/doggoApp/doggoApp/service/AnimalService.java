package com.example.doggoApp.doggoApp.service;

import com.example.doggoApp.doggoApp.domain.Animal;

public interface AnimalService {
    Animal getById(Long Id);

    Animal create(Animal animal);

    Animal update(Long Id, Animal animal);

    void delete(Animal animal);
}
