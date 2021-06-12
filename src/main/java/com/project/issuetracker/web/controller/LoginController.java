package com.project.issuetracker.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    private final SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @GetMapping("/login")
    public String login(@RequestParam(required = false) final String errorMsg, Model model) {
        if (errorMsg != null) {
            model.addAttribute("errorMsg", errorMsg);
        }

        return "account/login";
    }

    @PostMapping("/logout")
    public HttpStatus logout(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            logoutHandler.logout(request, response, auth);
        }

        return HttpStatus.ACCEPTED;
    }
}
