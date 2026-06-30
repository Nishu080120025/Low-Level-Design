package org.example.service.impl;

import org.example.models.Question;
import org.example.service.QuestionService;
import org.example.service.SearchService;
import org.example.strategy.SearchStrategy;

import java.util.List;
import java.util.Optional;

public class SearchServiceimpl implements SearchService {

    private final QuestionService questionService;
    public SearchServiceimpl(QuestionService questionService){
        this.questionService=questionService;
    }

    @Override
    public List<Optional<Question>> searchQuestions(SearchStrategy searchStrategy, String query){
        List<Optional<Question>>allQuestions=questionService.getAllQuestions();
        return searchStrategy.search(allQuestions, query);
    }
}
