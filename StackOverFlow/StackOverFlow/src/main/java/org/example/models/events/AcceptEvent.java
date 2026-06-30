package org.example.models.events;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.example.models.Answer;
import org.example.models.Question;
import org.example.models.User;

@Getter
@ToString
public class AcceptEvent {
    public Question question;
    public Answer acceptedAnswer;
    public User author;

    public AcceptEvent(Question question, Answer acceptedAnswer, User author) {
        this.question = question;
        this.acceptedAnswer = acceptedAnswer;
        this.author = author;
    }

}
