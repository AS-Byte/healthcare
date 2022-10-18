package com.sahti.chezvous.service;

import com.sahti.chezvous.dto.SoinsRequest;
import com.sahti.chezvous.dto.SoinsResponse;
import com.sahti.chezvous.exceptions.SoinsNotFoundException;
import com.sahti.chezvous.exceptions.SubredditNotFoundException;
import com.sahti.chezvous.mapper.SoinsMapper;
import com.sahti.chezvous.model.Patient;
import com.sahti.chezvous.model.Soins;
import com.sahti.chezvous.model.TypeSoins;
import com.sahti.chezvous.repository.PatientRepository;
import com.sahti.chezvous.repository.SoinsRepository;
import com.sahti.chezvous.repository.TypeSoinsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static java.util.stream.Collectors.toList;


@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class SoinsService {

    private final TypeSoinsRepository typeSoinsRepository;
    private final AuthService authService;
    private final SoinsMapper soinsMapper;
    private final SoinsRepository soinsRepository;
    private final PatientRepository patientRepository;

    public Soins save(SoinsRequest soinsRequest) {
        TypeSoins typeSoins = typeSoinsRepository.findByName(soinsRequest.getTypeSoinsName())
                .orElseThrow(() -> new SubredditNotFoundException("Not Found"));
        return soinsRepository.save(soinsMapper.map(soinsRequest, typeSoins, authService.getCurrentPatient()));
    }

    @Transactional(readOnly = true)
    public SoinsResponse getSoins(Long id) {
        Soins soins = soinsRepository.findById(id)
                .orElseThrow(() -> new SoinsNotFoundException(id.toString()));
        return soinsMapper.mapToDto(soins);
    }


    @Transactional(readOnly = true)
    public List<SoinsResponse> getAllSoins() {
        return soinsRepository.findAll()
                .stream()
                .map(soinsMapper::mapToDto)
                .collect(toList());
    }


    @Transactional(readOnly = true)
    public List<SoinsResponse> getSoinsByTypeSoins(Long typeSoinsId) {
        TypeSoins typeSoins = typeSoinsRepository.findById(typeSoinsId)
                .orElseThrow(() -> new SubredditNotFoundException(typeSoinsId.toString()));
        List<Soins> soinss = soinsRepository.findAllByTypeSoins(typeSoins);
        return soinss.stream().map(soinsMapper::mapToDto).collect(toList());
    }


    @Transactional(readOnly = true)
    public List<SoinsResponse> getSoinsByPatient(String patientName) {
        Patient patient = patientRepository.findByPatientname(patientName)
                .orElseThrow(() -> new UsernameNotFoundException(patientName));
        return soinsRepository.findByPatient(patient)
                .stream()
                .map(soinsMapper::mapToDto)
                .collect(toList());
    }

}
