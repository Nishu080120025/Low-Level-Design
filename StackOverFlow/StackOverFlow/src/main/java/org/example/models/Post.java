package org.example.models;


import lombok.Getter;
import lombok.ToString;
import org.example.util.IdUtil;

import java.time.LocalDateTime;

@Getter
@ToString
public abstract class Post{
    protected String id;
    protected User author;
    protected String content;
    protected LocalDateTime createdAt;

    public Post(User author,String content){
        this.id= IdUtil.generateId();
        this.author=author;
        this.content=content;
        this.createdAt=LocalDateTime.now();
    }
//    public Post(User author,String content){
//        super(IdUtil.generateId(),author,content);
//        this.author=author;
//        this.content=content;
//        this.commentList=new CopyOnWriteArrayList<>();
//        this.votesMap=new ConcurrentHashMap<String,VoteType>();
//        this.votesCount=new AtomicInteger(0);
//        this.postObservers=new CopyOnWriteArrayList<>();
//    }
//
//    public void addComment(Comment comment){
//        this.commentList.add(comment);
//    }
//    public void addObserver(PostObserver observer){
//        this.postObservers.add(observer);
//    }
//    public void notify(VoteEvent event){
//        for(PostObserver observer:postObservers){
//            observer.updateOnEventPost(event);
//        }
//    }
//
//    public synchronized void vote(User user, VoteType voteType){
//        if(votesMap.containsKey(user.getId())){
//            System.out.println("User has already voted on this post");
//            return ;
//        }
//        int scoreChange=0;
//        if(voteType==VoteType.DOWNVOTE){
//            scoreChange-=1;
//        }
//        else{
//            scoreChange+=1;
//        }
//        votesMap.put(user.getId(),voteType);
//        votesCount.addAndGet(scoreChange);
//        EventType eventType;
//        if(this instanceof Question){
//            eventType=voteType==VoteType.DOWNVOTE?EventType.QUESTION_DOWNVOTED:EventType.QUESTION_UPVOTED;
//        }
//        else{
//            eventType=voteType==VoteType.DOWNVOTE?EventType.ANSWER_DOWNVOTED:EventType.ANSWER_UPVOTED;
//        }
//        notify(new VoteEvent(this,user,eventType));
//    }
}
