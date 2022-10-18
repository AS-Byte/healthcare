package com.sahti.chezvous.repository;

import com.sahti.chezvous.model.TypeSoins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TypeSoinsRepository extends JpaRepository<TypeSoins, Long> {
    Optional<TypeSoins> findByName(String typeSoinsName);
}
