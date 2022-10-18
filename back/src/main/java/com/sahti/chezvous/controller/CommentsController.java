package com.sahti.chezvous.controller;

import com.sahti.chezvous.dto.CommentsDto;
import com.sahti.chezvous.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/api/comments/")
@AllArgsConstructor
public class CommentsController {
    private final CommentService commentService;


    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentsDto commentsDto) {
        commentService.save(commentsDto);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping("/by-soins/{soinsId}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForPost(@PathVariable Long soinsId) {
        return ResponseEntity.status(OK)
                .body(commentService.getAllCommentsForSoins(soinsId));
    }

    @GetMapping("/by-patient/{patientName}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForUser(@PathVariable String patientName){
        return ResponseEntity.status(OK)
                .body(commentService.getAllCommentsForPatient(patientName));
    }

    @PutMapping("/update")
    public ResponseEntity<CommentsDto> updateComment(@RequestBody CommentsDto commentsDto){
        CommentsDto upComment = commentService.updateComment(commentsDto);
        return new ResponseEntity<>(upComment, OK);
    }

}
