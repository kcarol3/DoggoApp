package com.example.doggoApp.doggoApp.controller;

import com.example.doggoApp.doggoApp.domain.Dog;
import com.example.doggoApp.doggoApp.model.DogDTO;
import com.example.doggoApp.doggoApp.service.AnimalService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/dog")
public class DogController {

    private final AnimalService animalService;

    public DogController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping(value = "")
    public ResponseEntity<String> createDog(@RequestBody DogDTO dogDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Dog dog = modelMapper.map(dogDTO, Dog.class);

        animalService.create(dog);

        return new ResponseEntity<>("Success created", HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DogDTO> getDog(@PathVariable Long id) {
        try {
            Dog dog = (Dog) animalService.getById(id);

            ModelMapper modelMapper = new ModelMapper();
            DogDTO dogDTO = modelMapper.map(dog, DogDTO.class);

            return new ResponseEntity<>(dogDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateDog(@RequestBody DogDTO dogDTO, @PathVariable Long id) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Dog updateDog = modelMapper.map(dogDTO, Dog.class);

            animalService.update(id, updateDog);

            return new ResponseEntity<>("Success update", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable Long id) {
        try {
            Dog dog = (Dog) animalService.getById(id);
            animalService.delete(dog);

            return new ResponseEntity<>("Success delete", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
