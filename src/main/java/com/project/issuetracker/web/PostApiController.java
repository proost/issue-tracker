package com.project.issuetracker.web;

import com.project.issuetracker.service.post.PostService;
import com.project.issuetracker.web.dto.post.PostResponse;
import com.project.issuetracker.web.dto.post.PostSaveRequest;
import com.project.issuetracker.web.dto.post.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostSaveRequest request) {
        return postService.save(request);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequest request) {
        return postService.update(id, request);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponse findById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }

}
