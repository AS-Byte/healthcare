package com.sahti.chezvous.service;

import com.sahti.chezvous.dto.CommentsDto;
import com.sahti.chezvous.exceptions.SoinsNotFoundException;
import com.sahti.chezvous.mapper.CommentMapper;
import com.sahti.chezvous.model.Comment;
import com.sahti.chezvous.model.NotificationEmail;
import com.sahti.chezvous.model.Patient;
import com.sahti.chezvous.model.Soins;
import com.sahti.chezvous.repository.CommentRepository;
import com.sahti.chezvous.repository.PatientRepository;
import com.sahti.chezvous.repository.SoinsRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {

    private static final String POST_URL = "";
    public final SoinsRepository soinsRepository;
    public final CommentMapper commentMapper;
    public final AuthService authService;
    public final CommentRepository commentRepository;
    public final MailContentBuilder mailContentBuilder;
    public final MailService mailService;
    public final PatientRepository patientRepository;

    public void save(CommentsDto commentsDto){
        Soins soins = soinsRepository.findById(commentsDto.getSoinsId())
                .orElseThrow(() -> new SoinsNotFoundException(commentsDto.getSoinsId().toString()));

        Comment comment = commentMapper.map(commentsDto , soins, authService.getCurrentPatient());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(soins.getPatient().getPatientname()+ " dépose une réservation de soins." + POST_URL);
        sendCommentNotification(message, soins.getPatient());

    }

    private void sendCommentNotification(String message, Patient patient) {
        mailService.sendMail(new NotificationEmail(patient.getPatientname() + " Demande de réservation", patient.getEmail(), message));
    }
    private void sendConfNotification(String message, Patient patient) {
        mailService.sendMail(new NotificationEmail(authService.getCurrentPatient().getPatientname() + " Notification d'acceptation", authService.getCurrentPatient().getEmail(), message));
    }

    public List<CommentsDto> getAllCommentsForSoins(Long soinsId) {
        Soins soins = soinsRepository.findById(soinsId).orElseThrow(() -> new SoinsNotFoundException(soinsId.toString()));
        return commentRepository.findBySoins(soins)
                .stream()
                .map(commentMapper::mapToDto).collect(toList());
    }

    public List<CommentsDto> getAllCommentsForPatient(String patientName) {
        Patient patient = patientRepository.findByPatientname(patientName)
                .orElseThrow(() -> new UsernameNotFoundException(patientName));
        return commentRepository.findAllByPatient(patient)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }

    public CommentsDto updateComment(CommentsDto commentsDto){
        Soins soins = soinsRepository.findById(commentsDto.getSoinsId())
                .orElseThrow(() -> new SoinsNotFoundException(commentsDto.getSoinsId().toString()));

        Comment comment = commentMapper.map(commentsDto , soins, authService.getCurrentPatient());
        String message = mailContentBuilder.build(authService.getCurrentPatient().getPatientname()+ " Accepte votre soins." + POST_URL);
        sendConfNotification(message, soins.getPatient());
        commentRepository.save(comment);
        return commentMapper.mapToDto(comment);
    }
}