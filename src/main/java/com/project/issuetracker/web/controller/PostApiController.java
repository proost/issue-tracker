package com.project.issuetracker.web.controller;

import com.project.issuetracker.service.post.PostService;
import com.project.issuetracker.web.dto.post.PostResponse;
import com.project.issuetracker.web.dto.post.PostSaveRequest;
import com.project.issuetracker.web.dto.post.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public HttpStatus save(@RequestBody PostSaveRequest request) {
        postService.save(request);

        return HttpStatus.ACCEPTED;
    }

    @PutMapping("/api/v1/posts/{id}")
    public HttpStatus update(@PathVariable final Long id, @RequestBody final PostUpdateRequest request) {
        postService.update(id, request);

        return HttpStatus.ACCEPTED;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponse findById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        postService.delete(id);

        return HttpStatus.ACCEPTED;
    }
}
