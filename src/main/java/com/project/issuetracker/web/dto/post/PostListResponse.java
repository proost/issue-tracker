package com.project.issuetracker.web.dto.post;

import com.project.issuetracker.domain.post.Post;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString(exclude = {"modifiedDate"})
@Getter
public class PostListResponse {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public PostListResponse(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
