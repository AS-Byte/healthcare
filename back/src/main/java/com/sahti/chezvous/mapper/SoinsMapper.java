package com.sahti.chezvous.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.sahti.chezvous.dto.SoinsRequest;
import com.sahti.chezvous.dto.SoinsResponse;
import com.sahti.chezvous.model.Patient;
import com.sahti.chezvous.model.Soins;
import com.sahti.chezvous.model.TypeSoins;
import com.sahti.chezvous.model.VoteType;
import com.sahti.chezvous.repository.CommentRepository;
import com.sahti.chezvous.repository.VoteRepository;
import com.sahti.chezvous.service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/*
import com.sahti.chezvous.model.VoteType.DOWNVOTE;
import com.sahti.chezvous.model.VoteType.UPVOTE;
*/

@Mapper(componentModel = "spring")
public abstract class SoinsMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "typeSoins", source = "typeSoins")
    @Mapping(target = "patient", source = "patient")
    @Mapping(target = "description", source = "soinsRequest.description")
    @Mapping(target = "voteCount", constant = "0")
    public abstract Soins map(SoinsRequest soinsRequest, TypeSoins typeSoins, Patient patient);

    @Mapping(target = "id", source = "soinsId")
    @Mapping(target = "typeSoinsName", source = "typeSoins.name")
    @Mapping(target = "patientName", source = "patient.patientname")
    @Mapping(target = "commentCount", expression = "java(commentCount(soins))")
    @Mapping(target = "duration", expression = "java(getDuration(soins))")
/*    @Mapping(target = "upVote", expression = "java(isSoinsUpVoted(soins))")
    @Mapping(target = "downVote", expression = "java(isSoinsDownVoted(soins))")*/
    public abstract SoinsResponse mapToDto(Soins soins);

    Integer commentCount(Soins soins) {
        return commentRepository.findBySoins(soins).size();
    }

    String getDuration(Soins soins) {
        return TimeAgo.using(soins.getCreatedDate().toEpochMilli());
    }

/*    boolean isPostUpVoted(Soins soins) {
        return checkVoteType(soins, UPVOTE);
    }

    boolean isPostDownVoted(Soins soins) {
        return checkVoteType(soins, DOWNVOTE);
    }*/

/*    private boolean checkVoteType(Soins soins, VoteType voteType) {
        if (authService.isLoggedIn()) {
            Optional voteForPostByUser = voteRepository.findTopBySoinsAndPatientOrderByVoteIdDesc(soins,
                    authService.getCurrentPatient());
            return voteForPostByUser.filter(vote -> vote.getVoteType().equals(voteType))
                    .isPresent();
        }
        return false;
    }*/
}
