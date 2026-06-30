package org.example.models;

import lombok.Getter;
import lombok.ToString;
import org.example.enums.VoteType;
import org.example.util.IdUtil;

@Getter
@ToString
public class Vote {
    private String voteId;
    private String voterId;
    private VoteType voteType;
    private String postId;

    public Vote(String voterId, VoteType voteType, String postId) {
        this.voteId = IdUtil.generateId();
        this.voterId = voterId;
        this.voteType = voteType;
        this.postId = postId;
    }

}
