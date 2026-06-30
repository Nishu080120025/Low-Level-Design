package org.example.models;

import lombok.Getter;
import lombok.ToString;
import org.example.enums.VoteType;
import org.example.models.modelsinterface.Commentable;
import org.example.models.modelsinterface.Voteable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@ToString
public class Answer extends Post implements Voteable, Commentable {
    private volatile Boolean isAccepted;
    private User author;
    private String content;
    private List<Comment>commentList;
    private List<Vote> votes;
    private String questionId;


    public Answer(User author,String content,String questionId){
        super(author,content);
        this.isAccepted=false;
        this.commentList=new CopyOnWriteArrayList<>();
        this.votes=new CopyOnWriteArrayList<>();
    }

    @Override
    public String getId(){
        return this.id;
    }

    @Override
    public void addVote(Vote vote){
        this.votes.add(vote);
    }

    @Override
    public void addComment(Comment comment){
        this.commentList.add(comment);
    }

    @Override
    public int CountTotalVotes(){
        return this.votes.stream().mapToInt(vote->vote.getVoteType()== VoteType.UPVOTE?1:-1).sum();
    }

    @Override
    public List<Vote> getVotes() {
        return this.votes;
    }

    @Override
    public User getAuthor() {
        return this.author;
    }

    @Override
    public List<Comment> getComments() {
        return this.commentList;
    }

    public void AcceptTheAnswer(){
        this.isAccepted=true;
    }

    public boolean isAccepted(){
        return this.isAccepted;
    }


}
