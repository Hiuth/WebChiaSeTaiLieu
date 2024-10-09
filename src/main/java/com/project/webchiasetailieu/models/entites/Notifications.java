package com.project.webchiasetailieu.models.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "Notifications")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotiID")
    private int notiID;

    @ManyToOne
    @JoinColumn(name = "AccountID", nullable = false)
    private Account account;  // Mỗi thông báo liên quan đến một tài khoản

    @Column(name = "Title", nullable = false, length = 255)
    private String title;

    @Column(name = "Content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "Date", nullable = false)
    private LocalDate date;

    @Column(name = "Time", nullable = false)
    private LocalTime time;

    @Column(name = "NotiType", nullable = false,length = 50)
    private String notiType;

    @PrePersist
    protected void onCreate() {
        date = LocalDate.now();
        time = LocalTime.now();
    }
}
