package com.samta.assignment.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "questions")
@Data
public class Question {

    @Id
    private Long id;

    private String answer;

    private String question;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Column(name = "question_value")
    private Integer value; // Renamed the column to avoid conflicts with reserved keyword "value"

    @Column(name = "airdate")
    private String airdate;

    @Column(name = "created_at")
    private String created_at;

    @Column(name = "updated_at")
    private String updated_at;

    private Long category_id;

    private Long game_id;

    private int invalid_count;

    @ManyToOne
    @JoinColumn(name = "category_category_id")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Category category; // This field will be populated from the Category table based on category_id


}
