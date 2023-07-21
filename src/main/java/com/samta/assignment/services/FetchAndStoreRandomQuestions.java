package com.samta.assignment.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samta.assignment.dto.GetQuestionResponseDTO;
import com.samta.assignment.dto.PostAnswerResponseDTO;
import com.samta.assignment.entities.Category;
import com.samta.assignment.entities.Question;
import com.samta.assignment.repositories.CategoryRepository;
import com.samta.assignment.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class FetchAndStoreRandomQuestions {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Value("${custom.api-url}")
    private String API_URL;

    private Map<Long, Boolean> hmap = new HashMap<>();
    public List<Question> fetchRandomQuestion()
    {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Question> questionList = new ArrayList<>();
        try
        {
            for(int i=0; i<5; i++)
            {
                String json = restTemplate.getForObject(API_URL, String.class);
                List<Question> questions = objectMapper.readValue(json, new TypeReference<List<Question>>() {});
                for (Question question : questions) {
                    // Fetch the corresponding Category based on category_id
                    Category category = question.getCategory();
                    categoryRepository.save(category);
                    questionRepository.save(question);
                    questionList.add(question);

                }
            }

        }
        catch (JsonProcessingException ex)
        {
            throw new RuntimeException(ex);
        }
        return questionList;
    }

    public GetQuestionResponseDTO fetchQuestion()
    {
       List<Question> questionList = questionRepository.findAll();
       int totalQuestions = questionList.size();

       if(totalQuestions==0) return null;
       Question question = questionList.get(0);

       GetQuestionResponseDTO getQuestionResponseDTO = new GetQuestionResponseDTO();
       getQuestionResponseDTO.setQuestion_id(question.getId());
       getQuestionResponseDTO.setQuestion(question.getQuestion());

       return getQuestionResponseDTO;
    }

    public PostAnswerResponseDTO fetchNextQuestion(Long questionId)
    {
        if(!hmap.containsKey(questionId)) hmap.put(questionId, true);
        List<Question> questionList = questionRepository.findAll();
        PostAnswerResponseDTO postAnswerResponseDTO = new PostAnswerResponseDTO();
        for(Question question : questionList)
        {
            if(question.getId().equals(questionId)) postAnswerResponseDTO.setCorrect_answer(question.getAnswer());
            if(!hmap.containsKey(question.getId()))
            {
                GetQuestionResponseDTO getQuestionResponseDTO = new GetQuestionResponseDTO();
                getQuestionResponseDTO.setQuestion_id(question.getId());
                getQuestionResponseDTO.setQuestion(question.getQuestion());
                postAnswerResponseDTO.setGetQuestionResponseDTO(getQuestionResponseDTO);
                return postAnswerResponseDTO;
            }
        }
        return null;
    }

}
