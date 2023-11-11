package com.example.doggoApp.doggoApp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dog")
@Getter
@Setter
@NoArgsConstructor
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private byte[] image;
    private String age;
    private String breed;
    private String sex;
    private Boolean isSterilized;
    private Boolean isVaccinated;
    private Boolean isDeleted;

    @OneToOne(mappedBy = "dog")
    private Adoption adoption;

    @OneToOne(mappedBy = "dog")
    private Announcement announcement;
}
