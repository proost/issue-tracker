package com.project.issuetracker.web.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = {"content"})
@Getter
@NoArgsConstructor
public class PostUpdateRequest {
    private String title;
    private String content;

    @Builder
    public PostUpdateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
