package com.example.doggoApp.doggoApp.service.impl;

import com.example.doggoApp.doggoApp.domain.Adoption;
import com.example.doggoApp.doggoApp.domain.User;
import com.example.doggoApp.doggoApp.service.AdoptionService;
import com.example.doggoApp.doggoApp.service.PDFCreator;
import com.example.doggoApp.doggoApp.service.UserService;
import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class AdoptionDocumentService {

    private AdoptionService adoptionService;
    private UserService userService;

    public AdoptionDocumentService(AdoptionService adoptionService, UserService userService) {
        this.adoptionService = adoptionService;
        this.userService = userService;
    }

    public ByteArrayInputStream createAdoptionDocument(Long adoptionId) throws DocumentException, IOException {
        Adoption adoption = adoptionService.getAdoptionById(adoptionId);
        User adopter = userService.getUserById(adoption.getUser().getId());
        User animalOwner = userService.getUserById(adoption.getAnimal().getUser().getId());

        PDFCreator creator = new PDFCreator();
        creator.createFile();

        creator.addTitlePage("Adoption confirmation");
        creator.addEmptyLine(1);

        creator.addHeader("Date: ");
        creator.addTextLine(adoption.getDate().toString());

        creator.addEmptyLine(1);
        creator.addHeader("Adopter:");
        creator.addTextLine("Name: " + adopter.getName());
        creator.addTextLine("Surname: " + adopter.getSurname());
        creator.addTextLine("Email: " + adopter.getEmail());
        creator.addEmptyLine(1);

        creator.addHeader("Animal owner");
        creator.addTextLine("Name: " + animalOwner.getName());
        creator.addTextLine("Surname: " + animalOwner.getSurname());
        creator.addTextLine("Email: " + animalOwner.getEmail());
        creator.addEmptyLine(1);

        creator.addHeader("Animal");
        creator.addTextLine("Name: " + adoption.getAnimal().getName());
        creator.addTextLine("Age: " + adoption.getAnimal().getAge());
        creator.addTextLine("Picture:");
        creator.addImage(adoption.getAnimal().getImage().getData());
        creator.addEmptyLine(6);
        creator.addTextLine("Adopter signature:                                      Animal owner signature:");
        creator.addEmptyLine(1);
        creator.addTextLine(".............................                                      .........................................");

        creator.closeFile();

        return creator.getCreatedFile();
    }
}
