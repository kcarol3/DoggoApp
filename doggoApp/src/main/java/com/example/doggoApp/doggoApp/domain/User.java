package com.example.doggoApp.doggoApp.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "user")
    private List<Adoption> adoptions;

    @OneToMany(mappedBy = "user")
    private List<Announcement> announcements;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Animal> animals;
}
