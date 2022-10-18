package com.sahti.chezvous.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Soins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long soinsId;
    private String soinsName;
    @Nullable
    private String url;
    @Nullable
    @Lob
    private String description;
    private Integer voteCount = 0;
    private Instant createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", referencedColumnName = "patientId")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private TypeSoins typeSoins;

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
