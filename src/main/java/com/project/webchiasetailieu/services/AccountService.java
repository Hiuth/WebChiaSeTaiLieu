package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.dtos.AccountDTO;
import com.project.webchiasetailieu.models.entites.Account;
import com.project.webchiasetailieu.repositories.AccountReposi;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountReposi accountReposi;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public enum BanType {
        TEMPORARY_10_DAYS,
        TEMPORARY_30_DAYS,
        PERMANENT
    }

    @Override
    public Account register(AccountDTO accountDTO) {
        if (accountDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        if (accountReposi.existsByEmail(accountDTO.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        if (accountReposi.existsByName(accountDTO.getName())) {
            throw new IllegalArgumentException("Name already in use");
        }
        if (!accountDTO.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (accountDTO.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }
        Account account = Account.builder()
                .name(accountDTO.getName())
                .email(accountDTO.getEmail())
               // .password(bCryptPasswordEncoder.encode(accountDTO.getPassword())) // Encode password
                .password(accountDTO.getPassword())
                .access(accountDTO.getAccess())
                .walletPoint(accountDTO.getWalletPoint())
                .isBanned(accountDTO.isBanned())
                .banUntil(accountDTO.getBanUntil())
                .build();

        return accountReposi.save(account);
    }

    @Override
    public List<Account> findAccountById(Integer id) {
        return accountReposi.findById(id).map(Collections::singletonList).orElse(Collections.emptyList());
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountReposi.findAll();
    }

    @Override
    public Account updatePassword(Integer id, String newPassword) {
        Optional<Account> accountOptional = accountReposi.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            //account.setPassword(bCryptPasswordEncoder.encode(newPassword)); // Encode password
            account.setPassword(newPassword);
            return accountReposi.save(account);
        } else {
            throw new IllegalArgumentException("Account not found");
        }
    }

    @Override
    public Account banAccount(Integer id, BanType banType) {
        Optional<Account> accountOptional = accountReposi.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setBanned(true);
            switch (banType) {
                case TEMPORARY_10_DAYS:
                    account.setBanUntil(LocalDateTime.now().plusDays(10));
                    break;
                case TEMPORARY_30_DAYS:
                    account.setBanUntil(LocalDateTime.now().plusDays(30));
                    break;
                case PERMANENT:
                    account.setBanUntil(null);
                    break;
            }
            return accountReposi.save(account);
        } else {
            throw new IllegalArgumentException("Account not found");
        }
    }

    @Override
    public Account unbanAccount(Integer id) {
        Optional<Account> accountOptional = accountReposi.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setBanned(false);
            account.setBanUntil(null);
            return accountReposi.save(account);
        } else {
            throw new IllegalArgumentException("Account not found");
        }
    }

    @Override
    public Account updateWalletPoint(Integer id, Integer walletPoint) {
        List<Account> existingAccount = findAccountById(id);
        existingAccount.get(0).setWalletPoint(walletPoint);
        accountReposi.save(existingAccount.get(0));
        return existingAccount.get(0);
    }
}