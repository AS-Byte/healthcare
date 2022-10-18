package com.sahti.chezvous.repository;

import com.sahti.chezvous.model.Patient;
import com.sahti.chezvous.model.Soins;
import com.sahti.chezvous.model.TypeSoins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoinsRepository extends JpaRepository<Soins, Long> {

    List<Soins> findAllByTypeSoins(TypeSoins typeSoins);

    List<Soins> findByPatient(Patient patient);
}

