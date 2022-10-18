package com.sahti.chezvous.service;


import com.sahti.chezvous.model.Patient;
import com.sahti.chezvous.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PatientRepository patientRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String patientname) {
        Optional<Patient> patientOptional = patientRepository.findByPatientname(patientname);
        Patient patient = patientOptional
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with username : " + patientname));

        return new org.springframework.security
                .core.userdetails.User(patient.getPatientname(), patient.getPassword(),
                patient.isEnabled(), true, true,
                true, getAuthorities("USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }
}
