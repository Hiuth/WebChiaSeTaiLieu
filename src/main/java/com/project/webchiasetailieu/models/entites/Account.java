package com.project.webchiasetailieu.models.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;


    @Column(name = "Email", unique = true, nullable = false)
    private String email;


    @Column(name = "Name", unique = true, nullable = false)
    private String name;

    @Column(name = "Password", nullable = false)
    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 kí tự")
    private String password;

    @Column(name = "Access", nullable = false)
    private String access;

    @Column(name = "WalletPoint", nullable = false)
    private int walletPoint;

    @PrePersist
    protected void onCreate() {
        if (this.walletPoint == 0) {
            this.walletPoint = 0; // Set default value to 0
        }
    }

    @Column(name = "IsBanned", nullable = false)
    private boolean isBanned;

    @Column(name = "BanUntil")
    private LocalDateTime banUntil;
}