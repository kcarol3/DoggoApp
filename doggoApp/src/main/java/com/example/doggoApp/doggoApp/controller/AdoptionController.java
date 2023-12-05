package com.example.doggoApp.doggoApp.controller;

import com.example.doggoApp.doggoApp.domain.Adoption;
import com.example.doggoApp.doggoApp.domain.Status;
import com.example.doggoApp.doggoApp.model.AdoptionDTO;
import com.example.doggoApp.doggoApp.service.AdoptionService;
import com.example.doggoApp.doggoApp.service.AnimalService;
import com.example.doggoApp.doggoApp.service.UserService;
import com.example.doggoApp.doggoApp.service.impl.AdoptionDocumentService;
import com.itextpdf.text.DocumentException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/adoption")
public class AdoptionController {
    private final AdoptionService adoptionService;
    private final AnimalService animalService;
    private final UserService userService;

    private final AdoptionDocumentService adoptionDocumentService;

    public AdoptionController(AdoptionService adoptionService, AnimalService animalService, UserService userService, AdoptionDocumentService adoptionDocumentService) {
        this.adoptionService = adoptionService;
        this.animalService = animalService;
        this.userService = userService;
        this.adoptionDocumentService = adoptionDocumentService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startAdoption(@RequestBody AdoptionDTO adoptionDTO) {
        try {
            Adoption adoption = new Adoption();
            adoption.setAnimal(animalService.getById(adoptionDTO.getAnimalId()));
            adoption.setUser(userService.getUserById(adoptionDTO.getUserId()));
            adoption.setDate(adoptionDTO.getDate());

            adoptionService.startAdoption(adoption);

            return new ResponseEntity<>("Success start adoption", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{adoptionId}/updateStatus")
    public ResponseEntity<String> updateAdoptionStatus(@RequestParam Status newStatus, @PathVariable Long adoptionId) {
        try {
            adoptionService.updateStatus(adoptionId, newStatus);

            return new ResponseEntity<>("Success update status", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{adoptionId}/document", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateDocument(@PathVariable Long adoptionId) throws DocumentException, IOException {
        ByteArrayInputStream bis = adoptionDocumentService.createAdoptionDocument(adoptionId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=adoption.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
