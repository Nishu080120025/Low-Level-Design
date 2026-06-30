package org.example.service;

import org.example.models.Question;
import org.example.strategy.SearchStrategy;

import java.util.List;
import java.util.Optional;

public interface SearchService {
    List<Optional<Question>> searchQuestions(SearchStrategy searchStrategy, String query);
}
