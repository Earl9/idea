package com.wild.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Comment {
    @Id
    public String id;
    public String CommentInfo;
    public String userName;
}
