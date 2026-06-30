package org.example.models.events;

import lombok.Getter;
import lombok.ToString;
import org.example.enums.TargetType;
import org.example.enums.VoteType;
import org.example.models.Post;
import org.example.models.User;

@Getter
@ToString
public class VoteEvent {
    private Post targetPost;
    private User user;
    private User targetUser;
    private TargetType type;
    private VoteType voteType;
    public VoteEvent(Post targetPost, User user, VoteType voteType, User targetUser,TargetType type){
        this.targetPost=targetPost;
        this.user=user;
        this.voteType=voteType;
        this.targetUser=targetUser;
        this.type=type;
    }

}
