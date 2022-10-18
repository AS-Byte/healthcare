package com.sahti.chezvous.controller;


import com.sahti.chezvous.dto.AuthenticationResponse;
import com.sahti.chezvous.dto.LoginRequest;
import com.sahti.chezvous.dto.RefreshTokenRequest;
import com.sahti.chezvous.dto.RegisterRequest;
import com.sahti.chezvous.model.Patient;
import com.sahti.chezvous.service.AuthService;
import com.sahti.chezvous.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful",
                OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatient() {
        List<Patient> patients = authService.findAllPatient();
        return new ResponseEntity<>(patients, OK);
    }

    @GetMapping("/find/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("patientId") Long patientId) {
        Patient patient = authService.findPatientById(patientId);
        return new ResponseEntity<>(patient, OK);
    }

    @GetMapping("/findPatient/{patientName}")
    public ResponseEntity<Patient> getPatientByName(@PathVariable("patientName") String patientName) {
        Patient patient = authService.findPatientByName(patientName);
        return new ResponseEntity<>(patient, OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }

}
