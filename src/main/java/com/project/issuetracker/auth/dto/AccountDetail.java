package com.project.issuetracker.auth.dto;

import com.project.issuetracker.domain.account.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class AccountDetail extends User {

    private final Account account;

    public AccountDetail(Account account, List<GrantedAuthority> roles) {
        super(account.getName(), account.getPassword(), roles);
        this.account = account;
    }
}
