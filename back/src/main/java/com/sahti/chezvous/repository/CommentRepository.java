package com.sahti.chezvous.repository;

import com.sahti.chezvous.model.Comment;
import com.sahti.chezvous.model.Patient;
import com.sahti.chezvous.model.Soins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findBySoins(Soins soins);

    List<Comment> findAllByPatient(Patient patient);
}
