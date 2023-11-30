package com.example.doggoApp.doggoApp.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Dog extends Animal{

    private String breed;
    private Boolean isSterilized;
    private Boolean isVaccinated;
}
