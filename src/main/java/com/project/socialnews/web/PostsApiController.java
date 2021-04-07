package com.project.socialnews.web;

import com.project.socialnews.service.posts.PostsService;
import com.project.socialnews.web.dto.PostsResponse;
import com.project.socialnews.web.dto.PostsSaveRequest;
import com.project.socialnews.web.dto.PostsUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequest request) {
        return postsService.save(request);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequest request) {
        return postsService.update(id, request);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponse findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

}
