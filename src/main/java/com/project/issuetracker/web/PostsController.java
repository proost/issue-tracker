package com.project.issuetracker.web;

import com.project.issuetracker.config.auth.dto.SessionUser;
import com.project.issuetracker.service.posts.PostsService;
import com.project.issuetracker.web.dto.PostsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class PostsController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/posts")
    public String posts(Model model, SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "posts";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponse response = postsService.findById(id);
        model.addAttribute("post", response);

        return "posts-update";
    }
}
