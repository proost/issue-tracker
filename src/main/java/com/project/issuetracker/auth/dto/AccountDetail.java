package com.project.issuetracker.auth.dto;

import com.project.issuetracker.domain.account.Account;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@ToString
@Getter
public class AccountDetail extends User {

    private final Long accountId;
    private final String email;
    private final String name;
    private final String team;

    public AccountDetail(Account account, List<GrantedAuthority> roles) {
        super(account.getEmail(), account.getPassword(), roles);

        this.email = account.getEmail();
        this.name = account.getName();
        this.team = account.getTeam();
        this.accountId = account.getId();
    }
}
