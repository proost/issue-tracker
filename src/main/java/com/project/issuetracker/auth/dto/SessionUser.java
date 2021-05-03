package com.project.issuetracker.auth.dto;

import com.project.issuetracker.domain.account.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
public class SessionUser implements Serializable {
    private final String name;
    private final String email;
    private final String team;

    @Builder
    public SessionUser(String name, String email, String team) {
        this.name = name;
        this.email = email;
        this.team = team;
    }

    public static SessionUser fromEntity(final Account account) {
        return SessionUser.builder()
                .name(account.getName())
                .email(account.getEmail())
                .team(account.getTeam())
                .build();
    }
}
