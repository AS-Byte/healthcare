package com.sahti.chezvous.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String patientname;
    private String adress;
    private String password;
    private int phone;
    private String ps;
    private int cin;
    private int assurance;
    private String diplome;
}
