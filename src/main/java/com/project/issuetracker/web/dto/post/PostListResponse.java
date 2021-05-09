package com.project.issuetracker.web.dto.post;

import com.project.issuetracker.domain.post.Post;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString(exclude = {"modifiedDate"})
@Getter
public class PostListResponse {
    private final Long id;
    private final String title;
    private final String authorName;
    private final LocalDateTime modifiedDate;

    public PostListResponse(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.authorName = entity.getAuthor().getName();
        this.modifiedDate = entity.getModifiedDate();
    }
}
