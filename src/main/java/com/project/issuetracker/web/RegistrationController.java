package com.project.issuetracker.web;

import com.project.issuetracker.service.account.AccountService;
import com.project.issuetracker.web.dto.account.AccountSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class RegistrationController {

    private final AccountService accountService;

    @GetMapping("/registration")
    public String registration() {
        return "account-registration";
    }

    @PostMapping("/registration")
    public void registerAccount(
        @RequestParam("username") final String name,
        @RequestParam("email") final String email,
        @RequestParam("password") final String password,
        @RequestParam("teamName") final String team,
        @RequestParam(value = "picture", required = false) final String picture,
        HttpServletResponse response
    ) throws IOException {
        final AccountSaveRequest accountSaveRequest = AccountSaveRequest.builder()
                .name(name)
                .email(email)
                .password(password)
                .team(team)
                .picture(picture)
                .build();

        accountService.createUser(accountSaveRequest);

        response.sendRedirect("/posts");
    }
}
