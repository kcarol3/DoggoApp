package com.example.doggoApp.doggoApp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String type;

    @Lob
    @Column(length = 2097151)
    private byte[] data;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted = false;

    @OneToOne
    @JsonBackReference
    private Animal animal;
}
