package com.example.doggoApp.doggoApp.domain;

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
    private String username;
    private String email;
    private String password;
    private Boolean isDeleted;

    @OneToMany(mappedBy = "user")
    private List<Adoption> adoptions;

    @OneToMany(mappedBy = "user")
    private List<Announcement> announcements;
}
