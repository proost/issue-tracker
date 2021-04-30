package com.project.issuetracker.service.account;

import com.project.issuetracker.domain.account.Account;
import com.project.issuetracker.domain.account.AccountRepository;
import com.project.issuetracker.web.dto.account.AccountSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public void createUser(final AccountSaveRequest accountSaveRequest) {
        final Account account = accountSaveRequest.toUserAccount();

        accountRepository.save(account);
    }
}
