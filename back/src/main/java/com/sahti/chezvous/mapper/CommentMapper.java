package com.sahti.chezvous.mapper;

import com.sahti.chezvous.dto.CommentsDto;
import com.sahti.chezvous.model.Comment;
import com.sahti.chezvous.model.Patient;
import com.sahti.chezvous.model.Soins;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "telPS", source = "commentsDto.telPS")
    @Mapping(target = "emailPS", source = "commentsDto.emailPS")
    @Mapping(target = "confirmPS", source = "commentsDto.confirmPS")
    @Mapping(target = "soins", source = "soins")
    @Mapping(target = "patient", source = "patient")
    Comment map(CommentsDto commentsDto, Soins soins, Patient patient);

    @Mapping(target = "soinsId", expression = "java(comment.getSoins().getSoinsId())")
    @Mapping(target = "patientName", expression = "java(comment.getPatient().getPatientname())")
    CommentsDto mapToDto(Comment comment);
}
