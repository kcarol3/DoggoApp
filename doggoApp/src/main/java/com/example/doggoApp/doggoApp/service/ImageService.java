package com.example.doggoApp.doggoApp.service;

import com.example.doggoApp.doggoApp.domain.Animal;
import com.example.doggoApp.doggoApp.domain.Image;
import com.example.doggoApp.doggoApp.repository.AnimalRepository;
import com.example.doggoApp.doggoApp.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private AnimalRepository animalRepository;

    public Image store(MultipartFile file, Long animalId) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Animal animal = animalRepository.findById(animalId).get();

        Image image = new Image(null, fileName, file.getContentType(), file.getBytes(),false, animal);

        Image savedImage = imageRepository.save(image);

        animal.setImage(savedImage);
        animalRepository.save(animal);

        return savedImage;
    }

    public Image getImage(Long id) {
        Image image =  imageRepository.findById(id).get();
        if (image.getIsDeleted()) {
            throw new NoSuchElementException();
        }
        return image;
    }

    public void deleteImage(Long id) {
            Image image = imageRepository.findById(id).get();
            image.setIsDeleted(true);
            imageRepository.save(image);
    }

    public Image getImageByAnimalId(Long animalId){
        Image image =  imageRepository.getImageByAnimalId(animalId);
        if (image == null) {
            throw new NoSuchElementException();
        }
        if(image.getIsDeleted()){
            throw new NoSuchElementException();
        }

        return image;
    }
}
