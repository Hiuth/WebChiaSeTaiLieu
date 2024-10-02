package com.project.webchiasetailieu.models.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity //xác định thực thể trong jpa
@Table(name = "Account") // nếu khác ten so với trong database thì hẳn xài
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @Column(name = "Email",unique = true, nullable = false)
    private String email;

    @Column(name="Password",nullable=false)
    @Size(min=6,message = "Mật khẩu phải có ít nhất 6 kí tự")
    private String password;

    @Column(name = "Access",nullable=false)
    private String access;

    @Column(name = "WalletPoint",nullable=false)
    private int walletPoint;
}
