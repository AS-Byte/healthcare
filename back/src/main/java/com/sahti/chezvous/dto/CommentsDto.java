package com.sahti.chezvous.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {
    private Long id;
    private Long soinsId;
    private Instant createdDate;
    private String text;
    private String patientName;
    private String telPS;
    private String emailPS;
    private Boolean confirmPS;
}
