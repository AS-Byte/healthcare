package com.sahti.chezvous.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class TypeSoins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    private Instant createdDate;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Soins> soinss;

    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

}
