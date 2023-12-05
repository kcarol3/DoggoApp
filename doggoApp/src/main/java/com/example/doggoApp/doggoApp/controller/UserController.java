package com.example.doggoApp.doggoApp.controller;

import com.example.doggoApp.doggoApp.domain.Adoption;
import com.example.doggoApp.doggoApp.domain.Animal;
import com.example.doggoApp.doggoApp.domain.Dog;
import com.example.doggoApp.doggoApp.domain.User;
import com.example.doggoApp.doggoApp.model.AdoptionDTO;
import com.example.doggoApp.doggoApp.model.DogDTO;
import com.example.doggoApp.doggoApp.model.UserDTO;
import com.example.doggoApp.doggoApp.service.AdoptionService;
import com.example.doggoApp.doggoApp.service.AnimalService;
import com.example.doggoApp.doggoApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AnimalService animalService;
    private final AdoptionService adoptionService;

    public UserController(UserService userService, AnimalService animalService, AdoptionService adoptionService) {
        this.userService = userService;
        this.animalService = animalService;
        this.adoptionService = adoptionService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            User newUser = modelMapper.map(userDTO, User.class);
            userService.createUser(newUser);

            return new ResponseEntity<>("Success created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{Id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long Id) {
        try {
            User user = userService.getUserById(Id);

            ModelMapper modelMapper = new ModelMapper();
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTO.setPassword(null);

            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{Id}")
    public ResponseEntity<String> updateUser(@PathVariable Long Id, @RequestBody UserDTO userDTODetails) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            User userDetails = modelMapper.map(userDTODetails, User.class);

            User updatedUser = userService.updateUser(Id, userDetails);

            return new ResponseEntity<>("Success update", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{Id}/adoptions")
    public ResponseEntity<List<AdoptionDTO>> getAllUserAdoptions(@PathVariable Long Id) {
        List<Adoption> adoptions = adoptionService.getAdoptionsByUserId(Id);

        List<AdoptionDTO> adoptionsDTO = new ArrayList<>();
        for (Adoption adoption : adoptions) {
            adoptionsDTO.add(new AdoptionDTO(
                    adoption.getId(),
                    adoption.getUser().getId(),
                    adoption.getAnimal().getId(),
                    adoption.getDate(),
                    adoption.getStatus()
            ));
        }

        return new ResponseEntity<>(adoptionsDTO, HttpStatus.OK);
    }

    @GetMapping("/{Id}/dogs")
    public ResponseEntity<List<DogDTO>> getAllUserDogs(@PathVariable Long Id) {
        List<Animal> animals = animalService.getAnimalsByUserId(Id);

        List<DogDTO> dogsDto = new ArrayList<>();
        for (Animal animal : animals) {
            Dog dog = (Dog) animal;
            ModelMapper modelMapper = new ModelMapper();
            DogDTO dogDTO = modelMapper.map(dog, DogDTO.class);

            dogsDto.add(dogDTO);
        }

        return new ResponseEntity<>(dogsDto, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long Id) {
        try {
            userService.deleteUser(Id);

            return new ResponseEntity<>("Success deleted", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
