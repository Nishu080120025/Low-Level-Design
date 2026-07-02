package org.example.models;

import lombok.Getter;
import lombok.ToString;
import org.example.enums.VoteType;
import org.example.models.modelsinterface.Commentable;
import org.example.models.modelsinterface.Voteable;
import org.example.util.IdUtil;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@ToString(callSuper = true)
public class Question extends Post implements Voteable, Commentable {

    private String id;
    private Set<Tags> tags;
    private String title;
    private List<Answer> answerList;
    private List<Comment> commentList;
    private List<Vote> votes;

    public Question(User author, String content, String title, Set<Tags> tags) {
        super(author, content);
        this.id = IdUtil.generateId();
        this.title = title;
        this.tags = tags;
        this.answerList = new CopyOnWriteArrayList<>();
        this.commentList = new CopyOnWriteArrayList<>();
        this.votes = new CopyOnWriteArrayList<>();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void addComment(Comment comment) {
        this.commentList.add(comment);
    }

    @Override
    public void addVote(Vote vote) {
        this.votes.add(vote);
    }

    @Override
    public int CountTotalVotes() {
        return this.votes.stream().mapToInt(vote -> vote.getVoteType() == VoteType.UPVOTE ? 1 : -1).sum();
    }

    @Override
    public List<Vote> getVotes() {
        return this.votes;
    }

    @Override
    public List<Comment> getComments() {
        return this.commentList;
    }

    @Override
    public User getAuthor() {
        return this.author;
    }

    public void addAnswer(Answer answer) {
        this.answerList.add(answer);
    }

    public List<Answer> getAnswerList() {
        return this.answerList;
    }

}
