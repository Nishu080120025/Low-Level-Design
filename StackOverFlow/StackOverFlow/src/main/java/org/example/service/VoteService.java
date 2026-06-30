package org.example.service;

import org.example.enums.VoteType;
import org.example.models.Post;
import org.example.models.User;
import org.example.models.Vote;
import org.example.models.modelsinterface.Voteable;

public interface VoteService {

    boolean validateVote(User voter, Post target, VoteType voteType);
    Vote vote (User voter, Post target, VoteType vote);
    boolean hasAlreadyVoted(User user ,Post target);
}
