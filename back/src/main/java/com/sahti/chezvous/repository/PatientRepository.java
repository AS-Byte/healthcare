package com.sahti.chezvous.repository;

import com.sahti.chezvous.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByPatientname(String patientname);
    void deletePatientByPatientId(Long patientId);
    Optional<Patient> findPatientByPatientId(Long patientId);


}
