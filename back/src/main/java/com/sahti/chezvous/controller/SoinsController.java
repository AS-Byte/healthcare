package com.sahti.chezvous.controller;

import com.sahti.chezvous.dto.SoinsRequest;
import com.sahti.chezvous.dto.SoinsResponse;
import com.sahti.chezvous.service.SoinsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.springframework.http.ResponseEntity.status;


@RestController
@RequestMapping("/api/soins/")
@AllArgsConstructor
public class SoinsController {

    private final SoinsService soinsService;

    @PostMapping
    public ResponseEntity<Void> createSoins(@RequestBody SoinsRequest soinsRequest) {
        soinsService.save(soinsRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SoinsResponse>> getAllSoins() {
        return status(HttpStatus.OK).body(soinsService.getAllSoins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoinsResponse> getSoins(@PathVariable Long id) {
        return status(HttpStatus.OK).body(soinsService.getSoins(id));
    }

    @GetMapping("by-typesoins/{id}")
    public ResponseEntity<List<SoinsResponse>> getSoinsByTypeSoins(@PathVariable Long id) {
        return status(HttpStatus.OK).body(soinsService.getSoinsByTypeSoins(id));
    }

    @GetMapping("by-patient/{name}")
    public ResponseEntity<List<SoinsResponse>> getSoinsByPatientName(@PathVariable String name) {
        return status(HttpStatus.OK).body(soinsService.getSoinsByPatient(name));
    }

}
