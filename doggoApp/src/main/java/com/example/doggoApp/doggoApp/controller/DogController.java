package com.example.doggoApp.doggoApp.controller;

import com.example.doggoApp.doggoApp.domain.Dog;
import com.example.doggoApp.doggoApp.domain.Image;
import com.example.doggoApp.doggoApp.model.DogDTO;
import com.example.doggoApp.doggoApp.service.AnimalService;
import com.example.doggoApp.doggoApp.service.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/dog")
public class DogController {

    private final AnimalService animalService;
    private final ImageService imageService;

    public DogController(AnimalService animalService, ImageService imageService) {
        this.animalService = animalService;
        this.imageService = imageService;
    }

    @PostMapping(value = "")
    public ResponseEntity<String> createDog(@ModelAttribute DogDTO dogDTO
            , @RequestParam("image") MultipartFile file
    ) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Dog dog = modelMapper.map(dogDTO, Dog.class);

            Dog createdDog = (Dog) animalService.create(dog);
            imageService.store(file, createdDog.getId());
        } catch (IOException e){
            return new ResponseEntity<>("Cannot upload file", HttpStatus.BAD_REQUEST);
        }

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

    @GetMapping(value = "/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id){
        try{
            Image image = imageService.getImageByAnimalId(id);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(image.getData(),headers, HttpStatus.OK);
        } catch (NoSuchElementException e){
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
