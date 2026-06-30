package org.example.models;

import lombok.Getter;
import lombok.ToString;
import org.example.util.IdUtil;

@Getter
@ToString
public class Comment extends Post{
    private String parentId;
    public Comment(User author,String content,String parentId){
        super(author,content);
       this.parentId=parentId;
    }
}
