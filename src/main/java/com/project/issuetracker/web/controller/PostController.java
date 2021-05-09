package com.project.issuetracker.web.controller;

import com.project.issuetracker.service.post.PostService;
import com.project.issuetracker.web.dto.post.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping({"/","/posts"})
    public String posts(Model model, HttpSession httpSession) {
        String username = (String) httpSession.getAttribute("username");
        Assert.notNull(username, "세션이 만료되었습니다.");

        model.addAttribute("posts", postService.findAllDesc());
        model.addAttribute("username", username);

        return "post/posts";
    }

    @GetMapping("/posts/save")
    public String postsSave(final HttpSession session, Model model) {
        final String username = (String) session.getAttribute("username");

        Assert.notNull(username, "세션이 만료되었습니다.");

        model.addAttribute("username", username);

        return "post/post-save";
    }

    @GetMapping("/posts/{id}")
    public String postDetail(@PathVariable final Long id, Model model, final HttpSession session) {
        final Long accountId = (Long) session.getAttribute("accountId");

        Assert.notNull(accountId, "세션이 만료되었습니다.");

        final PostResponse response = postService.findById(id);
        model.addAttribute("post", response);

        if (response.getAuthorId() == accountId) {
            model.addAttribute("isAuthor", true);
        }

        return "post/post-detail";
    }
}
