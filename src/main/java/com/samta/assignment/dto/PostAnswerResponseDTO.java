package com.samta.assignment.dto;

import lombok.Data;

@Data
public class PostAnswerResponseDTO {
    private String correct_answer;
    private GetQuestionResponseDTO getQuestionResponseDTO;
}
