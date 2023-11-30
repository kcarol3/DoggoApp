package com.example.doggoApp.doggoApp.controller;

import com.example.doggoApp.doggoApp.domain.Adoption;
import com.example.doggoApp.doggoApp.domain.Status;
import com.example.doggoApp.doggoApp.model.AdoptionDTO;
import com.example.doggoApp.doggoApp.service.AdoptionService;
import com.example.doggoApp.doggoApp.service.AnimalService;
import com.example.doggoApp.doggoApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/adoption")
public class AdoptionController {
    private final AdoptionService adoptionService;
    private final AnimalService animalService;
    private final UserService userService;

    public AdoptionController(AdoptionService adoptionService, AnimalService animalService, UserService userService) {
        this.adoptionService = adoptionService;
        this.animalService = animalService;
        this.userService = userService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startAdoption(@RequestBody AdoptionDTO adoptionDTO){
        try{
            Adoption adoption = new Adoption();
            adoption.setAnimal(animalService.getById(adoptionDTO.getAnimalId()));
            adoption.setUser(userService.getUserById(adoptionDTO.getUserId()));
            adoption.setDate(adoptionDTO.getDate());

            adoptionService.startAdoption(adoption);

            return new ResponseEntity<>("Success start adoption", HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{adoptionId}/updateStatus")
    public ResponseEntity<String> updateAdoptionStatus(@RequestParam Status newStatus, @PathVariable Long adoptionId){
        try{
            adoptionService.updateStatus(adoptionId, newStatus);

            return new ResponseEntity<>("Success update status", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
