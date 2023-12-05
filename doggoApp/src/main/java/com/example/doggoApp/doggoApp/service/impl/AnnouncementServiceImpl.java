package com.example.doggoApp.doggoApp.service.impl;

import com.example.doggoApp.doggoApp.domain.Announcement;
import com.example.doggoApp.doggoApp.repository.AnnouncementRepository;
import com.example.doggoApp.doggoApp.service.AnnouncementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @Override
    public Announcement getById(Long Id) {
        Announcement announcement = announcementRepository.findById(Id).get();
        if (announcement.getIsDeleted()) {
            throw new NoSuchElementException();
        }
        return announcement;
    }

    @Override
    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAllByIsDeletedFalse();
    }

    @Override
    public Announcement create(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    @Override
    public Announcement update(Long Id, Announcement announcement) {
        announcement.setId(Id);
        return announcementRepository.save(announcement);
    }

    @Override
    public void delete(Announcement announcement) {
        announcement.setIsDeleted(true);
        announcementRepository.save(announcement);
    }
}
