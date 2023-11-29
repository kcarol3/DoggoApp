package com.example.doggoApp.doggoApp.controller;

import com.example.doggoApp.doggoApp.domain.Announcement;
import com.example.doggoApp.doggoApp.model.AnnouncementDTO;
import com.example.doggoApp.doggoApp.service.AnnouncementService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @PostMapping(value = "")
    public ResponseEntity<String> createAnnouncement(@RequestBody AnnouncementDTO announcementDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Announcement announcement = modelMapper.map(announcementDTO, Announcement.class);

        announcementService.create(announcement);

        return new ResponseEntity<>("Success created", HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AnnouncementDTO> getAnnouncement(@PathVariable Long id) {
        try {
            Announcement announcement = (Announcement) announcementService.getById(id);

            ModelMapper modelMapper = new ModelMapper();
            AnnouncementDTO announcementDTO = modelMapper.map(announcement, AnnouncementDTO.class);

            return new ResponseEntity<>(announcementDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateAnnouncement(@RequestBody AnnouncementDTO announcementDTO, @PathVariable Long id) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Announcement updateAnnouncement = modelMapper.map(announcementDTO, Announcement.class);

            announcementService.update(id, updateAnnouncement);

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
