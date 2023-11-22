package com.example.doggoApp.doggoApp.service;

import com.example.doggoApp.doggoApp.domain.Animal;
import com.example.doggoApp.doggoApp.domain.Dog;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface AnimalService {
    Animal getById(Long Id);
    Animal create(Animal animal);
    Animal update(Long Id, Animal animal);
    void delete(Animal animal);
}
