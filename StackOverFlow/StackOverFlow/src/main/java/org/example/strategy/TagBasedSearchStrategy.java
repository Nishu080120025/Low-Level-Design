package org.example.strategy;

import org.example.models.Question;
import org.example.service.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TagBasedSearchStrategy implements SearchStrategy{

    private final QuestionService questionService;

    public TagBasedSearchStrategy(QuestionService questionService) {
        this.questionService = questionService;
    }
    @Override
    public List<Optional<Question>> search(List<Optional<Question>> allQuestions, String tagName) {
        List<Optional<Question>>resultList=new ArrayList<>();
        for(Optional<Question>questionOpt:allQuestions){
            if(questionOpt.isPresent()){
                Question q=questionOpt.get();
                if(q.getTags().contains(tagName)){
                    resultList.add(Optional.of(q));
                }
            }
        }
        return resultList;
    }
}
