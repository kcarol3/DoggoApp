package com.example.doggoApp.doggoApp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "announcement")
@Getter
@Setter
@NoArgsConstructor
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdDate;

    private String details;

    @ManyToOne
    private User user;

    @OneToOne
    private Dog dog;

    private Boolean isDeleted;
}
