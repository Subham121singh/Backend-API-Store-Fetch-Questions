package com.samta.assignment.controllers;

import com.samta.assignment.dto.PostAnswerDTO;
import com.samta.assignment.dto.GetQuestionResponseDTO;
import com.samta.assignment.dto.PostAnswerResponseDTO;
import com.samta.assignment.entities.Question;
import com.samta.assignment.services.FetchAndStoreRandomQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MangeQuestion {

    @Autowired
    FetchAndStoreRandomQuestions fetchAndStoreRandomQuestions;

    @GetMapping("/fetchAndStoreInDb")
    public ResponseEntity<List<Question>> fetchRandomQuestions()
    {
        List<Question> result = fetchAndStoreRandomQuestions.fetchRandomQuestion();
        return ResponseEntity.ok().body(result);

    }

    @GetMapping("/play")
    public ResponseEntity<GetQuestionResponseDTO> fetchQuestion()
    {
        GetQuestionResponseDTO getQuestionResponseDTO = fetchAndStoreRandomQuestions.fetchQuestion();
        if(getQuestionResponseDTO==null) return ResponseEntity.status(404).body(null);
        return ResponseEntity.ok().body(getQuestionResponseDTO);
    }

    @PostMapping("/next")
    public ResponseEntity<PostAnswerResponseDTO> fetchNextQuestion(@RequestBody PostAnswerDTO answerDTO)
    {
        PostAnswerResponseDTO postAnswerResponseDTO = fetchAndStoreRandomQuestions.fetchNextQuestion(answerDTO.getQuestion_id());
        if(postAnswerResponseDTO==null) return ResponseEntity.status(404).body(null);
        return ResponseEntity.ok().body(postAnswerResponseDTO);
    }

}
