package com.sahti.chezvous.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String patientname;
    private String adress;
    private String password;
    private String email;
    private int phone;
    private String ps;
    private int cin;
    private int assurance;
    private String diplome;
    private Instant created;
    private boolean enabled;
}
