package com.example.doggoApp.doggoApp.repository;

import com.example.doggoApp.doggoApp.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, Long> {
    public Image getImageByAnimalId(Long animalId);
}
