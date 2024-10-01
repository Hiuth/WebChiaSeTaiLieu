package com.project.webchiasetailieu.models.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "Comments")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ComID")
    private int comID;

    @ManyToOne
    @JoinColumn(name = "AccountID", nullable = false)
    private Account account;  // 1 tài khoản có thể bình luận nhiều lần

    @ManyToOne
    @JoinColumn(name = "DocID", nullable = false)
    private Documents document;  // một tài liệu có nhiều bình luận
    @Column(name = "ComText", columnDefinition = "TEXT")
    private String comText;

    @Column(name = "Date")
    private LocalDate date;

    @Column(name = "Time")
    private LocalTime time;

    @PrePersist
    protected void onCreate() {
        date = LocalDate.now();
        time = LocalTime.now();
    }
}
