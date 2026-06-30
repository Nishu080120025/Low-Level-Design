package org.example.strategy;

import org.example.models.Question;

import java.util.List;
import java.util.Optional;

public interface SearchStrategy {

    List<Optional<Question>> search(List<Optional<Question>>allQuestions, String query);
}
