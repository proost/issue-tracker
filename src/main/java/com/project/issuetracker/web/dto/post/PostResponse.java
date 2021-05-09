package com.project.issuetracker.web.dto.post;

import com.project.issuetracker.domain.post.Post;
import lombok.Getter;
import lombok.ToString;

@ToString(exclude = {"content"})
@Getter
public class PostResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String authorName;
    private final long authorId;

    public PostResponse(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.authorName = entity.getAuthor().getName();
        this.authorId = entity.getAuthor().getId();
    }

}
