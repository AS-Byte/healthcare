package com.sahti.chezvous.repository;

import com.sahti.chezvous.model.Patient;
import com.sahti.chezvous.model.Soins;
import com.sahti.chezvous.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopBySoinsAndPatientOrderByVoteIdDesc(Soins soins, Patient currentPatient);

}
