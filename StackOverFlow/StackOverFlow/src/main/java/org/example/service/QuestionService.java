package org.example.service;

import org.example.models.*;
import org.example.observers.PostObserver;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface QuestionService {

    Question postQuestion(User author, String title, String content, Set<Tags> tags);
    Optional<Question> getQuestionById(String questionId);
    List<Optional<Question>>getAllQuestions();
    Answer postAnswer(String questionId ,User author,String content);
    boolean acceptAnswer(Question question, User questionAuthor, Answer answer);
    Comment postCommentOnQuestion(String questionId, User author, String content);
    Comment postCommentOnAnswer(String answerId,User author,String content,String questionId);

    void addObserver(PostObserver observer);
    Tags createTag(String name);


}
