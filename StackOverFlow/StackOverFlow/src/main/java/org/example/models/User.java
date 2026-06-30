package org.example.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.util.IdUtil;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@ToString
public class User {
    private String id;
    private String userName;
    private String email;
    @Setter
    private AtomicInteger reputationScore;

    public User(String userName,String email){
        this.id= IdUtil.generateId();
        this.userName=userName;
        this.email=email;
        this.reputationScore=new AtomicInteger(0);
    }
    public void updateReputation(int change) {
        this.reputationScore.addAndGet(change);
    }

}
