package com.example.doggoApp.doggoApp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "adoption")
@Getter
@Setter
@NoArgsConstructor
public class Adoption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    @ManyToOne
    private User user;

    @OneToOne
    private Dog dog;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;
}
