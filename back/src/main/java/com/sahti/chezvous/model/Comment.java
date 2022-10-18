package com.sahti.chezvous.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Instant createdDate;
    private String telPS;
    private String emailPS;
    private Boolean confirmPS;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "soinsId", referencedColumnName = "soinsId")
    private Soins soins;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", referencedColumnName = "patientId")
    private Patient patient;

}
