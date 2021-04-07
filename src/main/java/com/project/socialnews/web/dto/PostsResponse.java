package com.project.socialnews.web.dto;

import com.project.socialnews.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponse {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponse(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }

}
