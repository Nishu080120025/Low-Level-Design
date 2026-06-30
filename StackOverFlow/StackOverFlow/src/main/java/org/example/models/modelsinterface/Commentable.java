package org.example.models.modelsinterface;


import org.example.models.Comment;

import java.util.List;

public interface Commentable {
    void addComment(Comment comment);
    List<Comment> getComments();
}
