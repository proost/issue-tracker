package com.project.issuetracker.web.dto.post;

import com.project.issuetracker.domain.post.Post;
import lombok.Getter;
import lombok.ToString;

@ToString(exclude = {"content"})
@Getter
public class PostResponse {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostResponse(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }

}
