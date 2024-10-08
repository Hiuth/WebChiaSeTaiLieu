package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.dtos.AccountDTO;
import com.project.webchiasetailieu.models.entites.Account;

import java.time.LocalDateTime;
import java.util.List;



public interface IAccountService {
    Account register(AccountDTO accountDTO);
    List<Account> findAccountById(Integer id);
    List<Account> findAllAccounts();
    Account updatePassword(Integer id, String newPassword);
    Account banAccount(Integer id, AccountService.BanType banType);
    Account unbanAccount(Integer id);
    Account updateWalletPoint(Integer id, Integer walletPoint);

}