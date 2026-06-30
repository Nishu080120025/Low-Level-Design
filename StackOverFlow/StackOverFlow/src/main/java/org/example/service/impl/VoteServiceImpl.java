package org.example.service.impl;

import org.example.enums.TargetType;
import org.example.enums.VoteType;
import org.example.models.*;
import org.example.models.events.VoteEvent;
import org.example.observers.PostObserver;
import org.example.service.VoteService;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class VoteServiceImpl implements VoteService {

    private final ConcurrentHashMap<String, Set<String>>voteRegister;
    private final List<PostObserver> observerList;

    public VoteServiceImpl(){
        this.voteRegister=new ConcurrentHashMap<>();
        this.observerList=new CopyOnWriteArrayList<>();
    }

    @Override
    public boolean validateVote(User voter, Post target, VoteType voteType){
        if(!canBeVoted(target))return false;
        if(hasAlreadyVoted(voter,target))return false;
        if(voter.getId().equals(target.getAuthor().getId()))return false;
        return true;
    }

    public boolean canBeVoted(Post target){
        if(target instanceof Question || target instanceof Answer)return true;
        return false;
    }
    public void addObserver(PostObserver observer){
        this.observerList.add(observer);
    }

    public void notifyObservers(VoteEvent event){
        for(PostObserver observer:observerList){
            observer.onVoteUpdate(event);
        }
    }

    @Override
    public boolean hasAlreadyVoted(User voter,Post target){
        Set<String>voterSet=voteRegister.getOrDefault(target.getId(),Set.of());
        return voterSet.contains(voter.getId());
    }

    @Override
    public Vote vote(User voter,Post target, VoteType voteType){
        if(!validateVote(voter,target,voteType))throw new IllegalArgumentException("Invalid Vote");
        if(hasAlreadyVoted(voter,target))throw new  IllegalArgumentException("User has already voted on this post");

        Vote vote=new Vote(voter.getId(),voteType,target.getId());
        if(target instanceof Question q){
            q.addVote(vote);
        }
        if(target instanceof Answer a){
            a.addVote(vote);
        }
        voteRegister.putIfAbsent(target.getId(),ConcurrentHashMap.newKeySet());
        voteRegister.get(target.getId()).add(voter.getId());
        TargetType targetType=target instanceof Question?TargetType.QUESTION: TargetType.ANSWER;
        VoteEvent voteEvent=new VoteEvent(target,voter,voteType,target.getAuthor(),targetType);
        notifyObservers(voteEvent);
        return vote;

    }

    public int getVoteScore(Post target){
        if(target instanceof Question q){
            return q.CountTotalVotes();
        }
        if(target instanceof Answer a){
            return a.CountTotalVotes();
        }
        return 0;
    }



}
