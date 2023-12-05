package com.example.doggoApp.doggoApp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "announcement")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdDate;

    private String details;

    @ManyToOne
    private User user;

    @OneToOne
    private Animal animal;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean isDeleted = false;

}
