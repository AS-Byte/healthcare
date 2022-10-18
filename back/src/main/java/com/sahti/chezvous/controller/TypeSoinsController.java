package com.sahti.chezvous.controller;

import com.sahti.chezvous.dto.TypeSoinsDto;
import com.sahti.chezvous.service.TypeSoinsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/typesoins")
@AllArgsConstructor
@Slf4j
public class TypeSoinsController {

    private final TypeSoinsService typeSoinsService;

    @PostMapping
    public ResponseEntity<TypeSoinsDto> createTypeSoins(@RequestBody TypeSoinsDto typeSoinsDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(typeSoinsService.save(typeSoinsDto));
    }

    @GetMapping
    public ResponseEntity<List<TypeSoinsDto>> getAllTypeSoins(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(typeSoinsService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeSoinsDto> getTypeSoins(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(typeSoinsService.getTypeSoins(id));
    }

}
