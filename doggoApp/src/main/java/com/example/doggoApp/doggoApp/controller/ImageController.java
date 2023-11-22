package com.example.doggoApp.doggoApp.controller;

import com.example.doggoApp.doggoApp.domain.Image;
import com.example.doggoApp.doggoApp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("image") MultipartFile file, @RequestParam("animalId") Long animalId) {
        String message = "";
        try {
            imageService.store(file, animalId);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id){
        try{
            Image image = imageService.getImage(id);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(image.getData(),headers, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id){
            imageService.deleteImage(id);

            return new ResponseEntity<>("Success delete", HttpStatus.OK);
    }
}
