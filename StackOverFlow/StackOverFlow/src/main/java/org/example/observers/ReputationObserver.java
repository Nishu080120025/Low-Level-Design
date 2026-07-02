package org.example.observers;

import org.example.enums.TargetType;
import org.example.enums.VoteType;
import org.example.models.events.AcceptEvent;
import org.example.models.events.VoteEvent;

public class ReputationObserver implements PostObserver {
    private static final int QUESTION_UPVOTE_REPUTATION_CHANGE=2;
    private static final int QUESTION_DOWNVOTE_REPUTATION_CHANGE=-2;
    private static final int ANSWER_UPVOTE_REPUTATION_CHANGE=5;
    private static final int ANSWER_DOWNVOTE_REPUTATION_CHANGE=-2;
    private static final int ACCEPT_REPUTATION_CHANGE=15;

    @Override
    public void onVoteUpdate(VoteEvent voteEvent){
        if(voteEvent==null){
            return ;
        }
        TargetType type=voteEvent.getType();
        if(type== TargetType.QUESTION){
            int repuationChange;
            if(voteEvent.getVoteType()==VoteType.UPVOTE){
               repuationChange=QUESTION_UPVOTE_REPUTATION_CHANGE;
               voteEvent.getTargetUser().updateReputation(repuationChange);
            }
            else{
                repuationChange=QUESTION_DOWNVOTE_REPUTATION_CHANGE;
                voteEvent.getTargetUser().updateReputation(repuationChange);
            }
        }
        else if(type==TargetType.ANSWER){
            int repuationChange;
            if(voteEvent.getVoteType()==VoteType.UPVOTE){
                repuationChange=ANSWER_UPVOTE_REPUTATION_CHANGE;
                voteEvent.getTargetUser().updateReputation(repuationChange);
            }
            else{
                repuationChange=ANSWER_DOWNVOTE_REPUTATION_CHANGE;
                voteEvent.getTargetUser().updateReputation(repuationChange);
            }
        }

    }

    @Override
    public void onAcceptUpdate(AcceptEvent acceptEvent){
        if(acceptEvent==null){
            return;
        }
        acceptEvent.getAuthor().updateReputation(ACCEPT_REPUTATION_CHANGE);

    }
}
