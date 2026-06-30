package org.example.observers;

import org.example.models.events.AcceptEvent;
import org.example.models.events.VoteEvent;

public interface PostObserver {
    void onVoteUpdate(VoteEvent voteEvent);
    void onAcceptUpdate(AcceptEvent acceptEvent);
}
