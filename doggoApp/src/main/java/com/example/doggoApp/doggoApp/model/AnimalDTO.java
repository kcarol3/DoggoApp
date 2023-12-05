package com.example.doggoApp.doggoApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDTO {
    private Long id;
    private Long userId;
    private Integer age;
    private String name;
    private String sex;
}
