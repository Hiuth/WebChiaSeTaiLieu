package com.project.webchiasetailieu.models.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Table(name="Feedback")
@AllArgsConstructor
@NoArgsConstructor

public class FeedBack
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedId;

    @Column(name="FeedText", nullable=false, columnDefinition = "TEXT")
    private String feedText;

    @Column(name="Date", nullable=false)
    private Date date;

    @Column(name="Time",nullable=false)
    private LocalTime time;

    @Column(name="FeedType", nullable=false)
    private String feedType;

    @ManyToOne // Một tài khoản có thể gửi nhiều phản hồi (feedback)
    @JoinColumn(name = "AccountID", nullable = false)
    private Account account;

    @PrePersist
    protected void onCreate() {
        this.date = new Date(); // Chỉ gán lần đầu khi thực thể được tạo mới
        this.time = LocalTime.now();
    }

}
