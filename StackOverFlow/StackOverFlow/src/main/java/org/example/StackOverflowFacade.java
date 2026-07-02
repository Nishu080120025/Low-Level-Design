package org.example;

import org.example.enums.VoteType;
import org.example.models.*;
import org.example.observers.PostObserver;
import org.example.observers.ReputationObserver;
import org.example.service.QuestionService;
import org.example.service.SearchService;
import org.example.service.UserService;
import org.example.service.VoteService;
import org.example.service.impl.QuestionServiceImpl;
import org.example.service.impl.SearchServiceimpl;
import org.example.service.impl.UserServiceImpl;
import org.example.service.impl.VoteServiceImpl;
import org.example.strategy.SearchStrategy;
import org.example.strategy.TagBasedSearchStrategy;

import java.util.List;
import java.util.Set;

public class StackOverflowFacade {

    private final QuestionService questionService;
    private final VoteService voteService;
    private final UserService userService;
    private final SearchService searchService;

    private final PostObserver postObserver;
    private final SearchStrategy searchStrategy;

    public StackOverflowFacade() {
        this.questionService = new QuestionServiceImpl();
        this.voteService = new VoteServiceImpl();
        this.userService = new UserServiceImpl();
        this.searchService = new SearchServiceimpl(questionService);
        this.searchStrategy = new TagBasedSearchStrategy(questionService);
        this.postObserver=new ReputationObserver();

        voteService.addObserver(postObserver);
        questionService.addObserver(postObserver);

    }

    public User createUser(String name, String email){
        return userService.createUser(name,email);
    }
    public Tags createTag(String name){
        return questionService.createTag(name);
    }
    public Question postQuestion(String content, User author, String title, Set<Tags> tags){
        return questionService.postQuestion(author,title,content,tags);
    }
    public Question getQuestionById(String questionId){
        return questionService.getQuestionById(questionId).orElse(null);
    }
    public Answer postAnswer(String questionId, User author, String content){
        return questionService.postAnswer(questionId,author,content);
    }
    public Comment addCommentInQuestion(String questionId,User author,String content){
        return questionService.postCommentOnQuestion(questionId,author,content);
    }
    public Comment addCommentInAnswer(String questionId,String answerId,User author,String content) {
        return questionService.postCommentOnAnswer(answerId, author, content, questionId);
    }
    public Vote vote(User voter, Post target, VoteType voteType){

        return voteService.vote(voter,target,voteType);
    }

    public List<Question>searchQuestionsByTag(String tagName){
        return searchService.searchQuestions(searchStrategy,tagName).stream().filter(question -> question.isPresent()).map(question -> question.get()).toList();
    }
    public boolean acceptAnswer(Question question, User questionAuthor, Answer answer){
        return questionService.acceptAnswer(question,questionAuthor,answer);
    }


}
