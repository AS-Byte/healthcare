package com.sahti.chezvous.service;

import com.sahti.chezvous.dto.AuthenticationResponse;
import com.sahti.chezvous.dto.LoginRequest;
import com.sahti.chezvous.dto.RefreshTokenRequest;
import com.sahti.chezvous.dto.RegisterRequest;
import com.sahti.chezvous.exceptions.SpringRedditException;
import com.sahti.chezvous.exceptions.UserNotFoundException;
import com.sahti.chezvous.model.NotificationEmail;
import com.sahti.chezvous.model.Patient;
import com.sahti.chezvous.model.VerificationToken;
import com.sahti.chezvous.repository.PatientRepository;
import com.sahti.chezvous.repository.VerificationTokenRepository;
import com.sahti.chezvous.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public void signup(RegisterRequest registerRequest){
        Patient patient = new Patient();
        patient.setPatientname(registerRequest.getPatientname());
        patient.setAdress(registerRequest.getAdress());
        patient.setEmail(registerRequest.getEmail());
        patient.setPhone(registerRequest.getPhone());
        patient.setPs(registerRequest.getPs());
        patient.setCin(registerRequest.getCin());
        patient.setAssurance(registerRequest.getAssurance());
        patient.setDiplome(registerRequest.getDiplome());
        patient.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        patient.setCreated(Instant.now());
        patient.setEnabled(false);
        patientRepository.save(patient);

        String token = generateVerificationToken(patient);
        mailService.sendMail(new NotificationEmail("Please Activate your Account",
                patient.getEmail(), "Thank you for signing up to sahti.tn, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    public List<Patient> findAllPatient(){
        return patientRepository.findAll();
    }

    public Patient updatePatient(Patient patient){
        return patientRepository.save(patient);
    }

    public Patient findPatientById(Long patientId){
        return patientRepository.findPatientByPatientId(patientId).orElseThrow(() -> new UserNotFoundException("was not found"));
    }

    public Patient findPatientByName(String patientName){
        return patientRepository.findByPatientname(patientName).orElseThrow(() -> new UserNotFoundException("was not found"));
    }

    public void deletePatient(Long patientId){
        patientRepository.findPatientByPatientId(patientId);
    }

    @Transactional(readOnly = true)
    public Patient getCurrentPatient() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return patientRepository.findByPatientname(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
    }

    private String generateVerificationToken(Patient patient) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setPatient(patient);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    private void fetchPatientAndEnable(VerificationToken verificationToken) {
        Long patientid = verificationToken.getPatient().getPatientId();
        Patient patient = patientRepository.findById(patientid).orElseThrow(()-> new SpringRedditException("not found"));
        patient.setEnabled(true);
        patientRepository.save(patient);
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchPatientAndEnable(verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token")));
    }


    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getPatientname(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getPatientname())
                .build();    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithPatientName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }
    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
}
