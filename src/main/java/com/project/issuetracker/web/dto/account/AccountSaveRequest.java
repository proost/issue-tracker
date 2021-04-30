package com.project.issuetracker.web.dto.account;


import com.project.issuetracker.domain.account.Account;
import com.project.issuetracker.domain.account.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString(exclude = {"password", "team", "picture"})
@Getter
public class AccountSaveRequest {

    private String name;
    private String email;
    private String password;
    private String team;
    private String picture;

    @Builder
    public AccountSaveRequest(String name, String email, String password, String team, String picture) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.team = team;
        this.picture = picture;
    }

    public Account toUserAccount() {
        return Account.builder()
                .name(name)
                .email(email)
                .password(password)
                .team(team)
                .picture(picture)
                .role(Role.USER)
                .build();
    }

}
