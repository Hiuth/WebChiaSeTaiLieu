package com.project.webchiasetailieu.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private int accountId;
    private String email;
    private String Name;
    private String password;
    private String access;
    private int walletPoint;
    private boolean isBanned;
    private LocalDateTime banUntil;
}
