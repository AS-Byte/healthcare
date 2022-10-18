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
public class SoinsResponse {
    private Long id;
    private String soinsName;
    private String url;
    private String description;
    private String patientName;
    private String typeSoinsName;
    private Integer voteCount;
    private Integer commentCount;
    private String duration;
    /* private boolean upVote;
    private boolean downVote;*/
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
