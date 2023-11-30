package com.example.doggoApp.doggoApp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String age;
    private String sex;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted = false;

    @OneToOne(mappedBy = "animal")
    private Adoption adoption;

    @OneToOne(mappedBy = "animal")
    private Announcement announcement;

    @OneToOne(mappedBy = "animal")
    @JsonManagedReference
    private Image image;

    @ManyToOne
    @JsonBackReference
    private User user;

}
