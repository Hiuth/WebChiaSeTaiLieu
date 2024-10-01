package com.project.webchiasetailieu.models.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Table(name="Recharge_History")
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class RechargeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int depositId;

    @ManyToOne //một tài khoản được phép mua điểm nhiều lần
    @JoinColumn(name="AccountID",nullable=false)
    private Account account;

    @Column(name = "Point",nullable=false)
    private int point;

    @Column(name="Date",nullable = false)
    private Date date;
    @PrePersist
    protected void onCreate() {
        this.date = new Date(); // Chỉ gán lần đầu khi thực thể được tạo mới
    }
}
