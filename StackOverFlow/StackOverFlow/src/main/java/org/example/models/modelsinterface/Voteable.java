package org.example.models.modelsinterface;

import org.example.models.Vote;

import java.util.List;

public interface Voteable {
    int CountTotalVotes();
    void addVote(Vote vote);
    List<Vote> getVotes();
}
