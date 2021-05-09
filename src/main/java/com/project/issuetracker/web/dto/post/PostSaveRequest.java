package com.project.issuetracker.web.dto.post;

import com.project.issuetracker.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = {"content"})
@Getter
@NoArgsConstructor
public class PostSaveRequest {
    private String title;
    private String content;

    @Builder
    public PostSaveRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
