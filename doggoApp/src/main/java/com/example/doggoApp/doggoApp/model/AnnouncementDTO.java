package com.example.doggoApp.doggoApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDTO {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;
    private String details;
    private Long userId;
    private Long animalId;
    private Boolean isDeleted;
}
