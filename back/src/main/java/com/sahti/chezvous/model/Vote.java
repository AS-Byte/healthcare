package com.sahti.chezvous.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;
    private VoteType voteType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "soinsId", referencedColumnName = "soinsId")
    private Soins soins;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", referencedColumnName = "patientId")
    private Patient patient;
}
