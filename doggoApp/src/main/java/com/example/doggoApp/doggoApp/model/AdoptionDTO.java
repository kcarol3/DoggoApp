package com.example.doggoApp.doggoApp.model;

import com.example.doggoApp.doggoApp.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionDTO {
    private Long id;
    private Long userId;
    private Long animalId;
    private final Date date = new Date();
    private Status status;
}
