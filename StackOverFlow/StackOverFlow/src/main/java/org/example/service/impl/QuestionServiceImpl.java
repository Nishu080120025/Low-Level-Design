package org.example.service.impl;

import org.example.models.*;
import org.example.models.events.AcceptEvent;
import org.example.observers.PostObserver;
import org.example.service.QuestionService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class QuestionServiceImpl implements QuestionService {

    private final ConcurrentHashMap<String, Question> questionMap;
    private final CopyOnWriteArrayList<PostObserver> observers;

    public QuestionServiceImpl() {
        this.questionMap = new ConcurrentHashMap<>();
        this.observers = new CopyOnWriteArrayList<>();
    }

    @Override
    public Question postQuestion(User author, String title, String content, Set<Tags> tags) {
        if (!isValidQuestion(title, content, tags)) {
            throw new IllegalArgumentException("Invalid question details");
        }
        Question question = new Question(author, content, title, tags);
        if (isQuestionExist(question.getId())) {
            throw new IllegalArgumentException("Question with the same ID already exists");
        }
        questionMap.put(question.getId(), question);
        return question;
    }

    @Override
    public Optional<Question> getQuestionById(String questionId) {
        if (!isQuestionExist(questionId)) {
            return Optional.empty();
        }
        return Optional.of(questionMap.get(questionId));
    }

    @Override
    public List<Optional<Question>> getAllQuestions() {
        return questionMap.values().stream().map(Optional::of).toList();
    }

    private boolean isQuestionExist(String questionId) {
        return this.questionMap.containsKey(questionId);
    }

    private boolean isValidQuestion(String title, String content, Set<Tags> tags) {
        if (title == null || title.isEmpty() || content == null || content.isEmpty() || tags == null || tags.isEmpty()) {
            return false;
        }
        return true;
    }

    private void addObserver(PostObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public Answer postAnswer(String questionId, User author, String content) {
        if (!isAnswerValid(questionId, content)) throw new IllegalArgumentException("Invalid answer details");
        Question question = questionMap.get(questionId);
        Answer answer = new Answer(author, content, questionId);
        question.addAnswer(answer);
        return answer;
    }

    @Override
    public boolean acceptAnswer(Question question ,User author,Answer answer){
        if(question==null || answer==null || author==null)throw new IllegalArgumentException("Question, answer and author cannot be null");

        if(questionMap.get(question.getId()).getAuthor().getId()!=author.getId()){
            throw new IllegalArgumentException("Only the author of the question can accept an answer");
        }

        boolean belongs=question.getAnswerList().stream().anyMatch(ans->ans.getId().equals(answer.getId()));
        if(!belongs){
            throw new IllegalArgumentException("Answer does not belong to the question");
        }
        answer.AcceptTheAnswer();
        AcceptEvent event=new AcceptEvent(question,answer,author);
        notifyObservers(event);
        return true;
    }

    private boolean isAnswerValid(String questionId, String content) {
        if (questionId == null || !questionMap.containsKey(questionId) || content == null || content.isEmpty()||questionMap.get(questionId)==null) {
            return false;
        }

        return true;
    }

    public void notifyObservers(AcceptEvent event) {
        for (PostObserver observer : observers) {
            observer.onAcceptUpdate(event);
        }
    }

    @Override
    public Comment postCommentOnQuestion(String questionId, User author, String content){
        if(!isCommentValidOnQuestion(questionId,content))throw new IllegalArgumentException("Invalid comment details");
        Question question=questionMap.get(questionId);
        Comment comment=new Comment(author,content,questionId);
        question.addComment(comment);
        return comment;
    }

    @Override
    public Comment postCommentOnAnswer(String answerId,User author,String content,String questionId){
        if(!isCommentValidOnAnswer(answerId,content,questionId))throw new IllegalArgumentException("Invalid comment details");
        for(Answer answer:questionMap.get(questionId).getAnswerList()){
            if(answer.getId().equals(answerId)){
                Comment comment=new Comment(author,content,answerId);
                answer.addComment(comment);
                return comment;
            }
        }throw new IllegalArgumentException("Answer does not belong to the question");
    }

    public boolean isCommentValidOnQuestion(String questionId,String content){
        if(questionId==null || !questionMap.containsKey(questionId) || content==null || content.isEmpty()||questionMap.get(questionId)==null){
            return false;
        }
        return true;
    }
    public boolean isCommentValidOnAnswer(String answerId,String content,String questionId){
        if(answerId==null || content==null || content.isEmpty() || questionId==null || !questionMap.containsKey(questionId)){
            return false;
        }
        for(Answer answer:questionMap.get(questionId).getAnswerList()){
            if(answer.getId().equals(answerId)){
                return true;
            }
        }
        return false;
    }


}
