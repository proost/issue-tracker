package com.project.issuetracker.web;

import com.project.issuetracker.service.post.PostService;
import com.project.issuetracker.web.dto.post.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;
    private final HttpSession httpSession;

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", postService.findAllDesc());

//        if (user != null) {
//            model.addAttribute("userName", user.getName());
//        }

        return "post";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "post-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostResponse response = postService.findById(id);
        model.addAttribute("post", response);

        return "post-update";
    }
}
