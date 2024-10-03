package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.entites.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UnbanScheduler {

    @Autowired
    private IAccountService accountService;

    @Scheduled(fixedRate = 60000) // Run every 60 seconds
    public void unbanExpiredAccounts() {
        List<Account> accounts = accountService.findAllAccounts();
        for (Account account : accounts) {
            if (account.isBanned() && account.getBanUntil() != null && account.getBanUntil().isBefore(LocalDateTime.now())) {
                accountService.unbanAccount(account.getAccountId());
            }
        }
    }
}