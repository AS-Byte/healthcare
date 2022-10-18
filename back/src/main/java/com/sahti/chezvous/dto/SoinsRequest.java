package com.sahti.chezvous.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SoinsRequest {
    private Long soinsId;
    private String typeSoinsName;
    private String soinsName;
    private String url;
    private String description;
    private Boolean perfusion;
    private String pansement;
    private String stomie;
    private Boolean pansementMeches;
    private String injection;
    private String injecter;

    private Date journeeSoins;
    private String dureeSoins;
    private String adresseSoins;
    private String telSoins;

}
