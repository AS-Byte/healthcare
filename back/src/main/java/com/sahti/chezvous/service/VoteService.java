package com.sahti.chezvous.service;


import com.sahti.chezvous.dto.VoteDto;
import com.sahti.chezvous.exceptions.SoinsNotFoundException;
import com.sahti.chezvous.exceptions.SpringRedditException;
import com.sahti.chezvous.model.Soins;
import com.sahti.chezvous.model.Vote;
import com.sahti.chezvous.repository.SoinsRepository;
import com.sahti.chezvous.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.sahti.chezvous.model.VoteType.UPVOTE;

@Service
@AllArgsConstructor
public class VoteService {


    private final VoteRepository voteRepository;
    private final SoinsRepository soinsRepository;
    private final AuthService authService;

    @Transactional
    public void vote(VoteDto voteDto) {
        Soins soins = soinsRepository.findById(voteDto.getSoinsId())
                .orElseThrow(() -> new SoinsNotFoundException("Soins Not Found with ID - " + voteDto.getSoinsId()));
        Optional<Vote> voteBySoinsAndPatient = voteRepository.findTopBySoinsAndPatientOrderByVoteIdDesc(soins, authService.getCurrentPatient());
        if (voteBySoinsAndPatient.isPresent() &&
                voteBySoinsAndPatient.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already "
                    + voteDto.getVoteType() + "'d for this soins");
        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
            soins.setVoteCount(soins.getVoteCount() + 1);
        } else {
            soins.setVoteCount(soins.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto, soins));
        soinsRepository.save(soins);
    }

    private Vote mapToVote(VoteDto voteDto, Soins soins) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .soins(soins)
                .patient(authService.getCurrentPatient())
                .build();
    }

}
