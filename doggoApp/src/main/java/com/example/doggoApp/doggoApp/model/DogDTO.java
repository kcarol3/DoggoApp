package com.example.doggoApp.doggoApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DogDTO extends AnimalDTO {
    private String breed;
    private Boolean isVaccinated;
    private Boolean isSterilized;
}
