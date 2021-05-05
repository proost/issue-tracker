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
        model.addAttribute("posts", postService.findAllDesc());

        String username = (String) httpSession.getAttribute("username");
        Assert.notNull(username, "계정정보가 만료되었습니다.");

        model.addAttribute("username", username);

        return "post/posts";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "post/post-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostResponse response = postService.findById(id);
        model.addAttribute("post", response);

        return "post/post-update";
    }
}
