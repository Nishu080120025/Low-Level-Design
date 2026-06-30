package org.example.strategy;

import org.example.models.Question;
import org.example.service.QuestionService;

import java.util.List;
import java.util.Optional;

public class KeyWordBasedSearchStrategy implements SearchStrategy{

    private final QuestionService questionService;

    public KeyWordBasedSearchStrategy(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public List<Optional<Question>>search(List<Optional<Question>> allQuestions, String keyword) {
        List<Optional<Question>> resultList = new java.util.ArrayList<>();
        for (Optional<Question> questionOpt : allQuestions) {
            if (questionOpt.isPresent()) {
                Question q = questionOpt.get();
                if (q.getTitle().contains(keyword) || q.getContent().contains(keyword)) {
                    resultList.add(Optional.of(q));
                }
            }
        }
        return resultList;
    }
}
