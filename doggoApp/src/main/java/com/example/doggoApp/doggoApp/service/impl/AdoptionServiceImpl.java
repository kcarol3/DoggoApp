package com.example.doggoApp.doggoApp.service.impl;

import com.example.doggoApp.doggoApp.domain.Adoption;
import com.example.doggoApp.doggoApp.domain.Status;
import com.example.doggoApp.doggoApp.domain.User;
import com.example.doggoApp.doggoApp.repository.AdoptionRepository;
import com.example.doggoApp.doggoApp.service.AdoptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionServiceImpl implements AdoptionService {

    private final AdoptionRepository adoptionRepository;

    public AdoptionServiceImpl(AdoptionRepository adoptionRepository) {
        this.adoptionRepository = adoptionRepository;
    }

    @Override
    public void startAdoption(Adoption adoption) {
        User user = adoption.getUser();

        if (user.getAnimals().contains(adoption.getAnimal())) {
            throw new IllegalArgumentException("You cannot adopt your own dog!");
        }

        adoption.setStatus(Status.STARTED);
        adoptionRepository.save(adoption);
    }

    @Override
    public void cancelAdoption(Long adoptionId) {
        Adoption adoption = adoptionRepository.findById(adoptionId).get();
        adoption.setStatus(Status.CANCELED);
        adoptionRepository.save(adoption);
    }


    @Override
    public void updateStatus(Long adoptionId, Status newStatus) {
        Adoption adoption = adoptionRepository.findById(adoptionId).get();
        adoption.setStatus(newStatus);

        adoptionRepository.save(adoption);
    }

    @Override
    public List<Adoption> getAdoptionsByUserId(Long userId) {
        return adoptionRepository.findAdoptionsByUserId(userId);
    }

    @Override
    public Adoption getAdoptionById(Long adoptionId) {
        return adoptionRepository.findById(adoptionId).get();
    }
}
