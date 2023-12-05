package com.example.doggoApp.doggoApp.controller;

import com.example.doggoApp.doggoApp.domain.Announcement;
import com.example.doggoApp.doggoApp.model.AnnouncementDTO;
import com.example.doggoApp.doggoApp.service.AnimalService;
import com.example.doggoApp.doggoApp.service.AnnouncementService;
import com.example.doggoApp.doggoApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    private final AnnouncementService announcementService;
    private final AnimalService animalService;
    private final UserService userService;

    public AnnouncementController(AnnouncementService announcementService, AnimalService animalService, UserService userService) {
        this.announcementService = announcementService;
        this.animalService = animalService;
        this.userService = userService;
    }

    @PostMapping(value = "")
    public ResponseEntity<String> createAnnouncement(@RequestBody AnnouncementDTO announcementDTO) {
        Announcement announcement = new Announcement();
        announcement.setAnimal(animalService.getById(announcementDTO.getAnimalId()));
        announcement.setUser(userService.getUserById(announcementDTO.getUserId()));
        announcement.setCreatedDate(announcementDTO.getCreatedDate());
        announcement.setDetails(announcementDTO.getDetails());

        announcementService.create(announcement);

        return new ResponseEntity<>("Success created", HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AnnouncementDTO> getAnnouncement(@PathVariable Long id) {
        try {
            Announcement announcement = announcementService.getById(id);
            AnnouncementDTO announcementDTO = new AnnouncementDTO();
            announcementDTO.setId(announcement.getId());
            announcementDTO.setAnimalId(announcement.getAnimal().getId());
            announcementDTO.setUserId(announcement.getUser().getId());
            announcementDTO.setCreatedDate(announcement.getCreatedDate());
            announcementDTO.setDetails(announcement.getDetails());

            return new ResponseEntity<>(announcementDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "")
    public ResponseEntity<List<AnnouncementDTO>> getAnnouncements() {
        try {
            List<Announcement> announcements = announcementService.getAllAnnouncements();

            List<AnnouncementDTO> announcementDTOs = announcements.stream()
                    .map(announcement -> {
                        AnnouncementDTO announcementDTO = new AnnouncementDTO();
                        announcementDTO.setId(announcement.getId());
                        announcementDTO.setAnimalId(announcement.getAnimal().getId());
                        announcementDTO.setUserId(announcement.getUser().getId());
                        announcementDTO.setCreatedDate(announcement.getCreatedDate());
                        announcementDTO.setDetails(announcement.getDetails());
                        return announcementDTO;
                    })
                    .collect(Collectors.toList());

            return new ResponseEntity<>(announcementDTOs, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateAnnouncement(@RequestBody AnnouncementDTO announcementDTO, @PathVariable Long id) {
        try {
            Announcement announcementToUpdate = announcementService.getById(id);
            announcementToUpdate.setAnimal(animalService.getById(announcementDTO.getAnimalId()));
            announcementToUpdate.setUser(userService.getUserById(announcementDTO.getUserId()));
            announcementToUpdate.setCreatedDate(announcementDTO.getCreatedDate());
            announcementToUpdate.setDetails(announcementDTO.getDetails());

            announcementService.update(id, announcementToUpdate);

            return new ResponseEntity<>("Success update", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAnnouncement(@PathVariable Long id) {
        try {
            Announcement announcement = (Announcement) announcementService.getById(id);
            announcementService.delete(announcement);

            return new ResponseEntity<>("Success delete", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
