package com.project.issuetracker.web.controller;

import com.project.issuetracker.service.post.PostService;
import com.project.issuetracker.web.dto.post.PostResponse;
import com.project.issuetracker.web.dto.post.PostSaveRequest;
import com.project.issuetracker.web.dto.post.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public HttpStatus save(@RequestBody final PostSaveRequest request, final HttpSession session) {
        final Long accountId = (Long) session.getAttribute("accountId");

        Assert.notNull(accountId, "세션이 만료되었습니다.");

        postService.save(request, accountId);

        return HttpStatus.ACCEPTED;
    }

    @PutMapping("/api/v1/posts/{id}")
    public HttpStatus update(@PathVariable final long id, @RequestBody final PostUpdateRequest request, final HttpSession session) {
        final Long requestAccountId = (Long) session.getAttribute("accountId");

        Assert.notNull(requestAccountId, "세션이 만료되었습니다.");

        postService.update(id, request, requestAccountId);

        return HttpStatus.ACCEPTED;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponse findById(@PathVariable long id) {
        return postService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public HttpStatus delete(@PathVariable final long id, final HttpSession session) {
        final Long requestAccountId = (Long) session.getAttribute("accountId");

        Assert.notNull(requestAccountId, "세션이 만료되었습니다.");

        postService.delete(id, requestAccountId);

        return HttpStatus.ACCEPTED;
    }
}
