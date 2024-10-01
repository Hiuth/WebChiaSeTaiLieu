package com.project.webchiasetailieu.models.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Download_History")
@AllArgsConstructor
@NoArgsConstructor
public class DownloadHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DowID")
    private int dowID;

    @ManyToOne
    @JoinColumn(name = "DocID", nullable = false)
    private Documents documents;  // Mỗi lịch sử tải xuống liên kết với một tài liệu nhưng một tài liệu có thể xuất hiện nhiều lần trong lịch sử tải xuống

    @ManyToOne
    @JoinColumn(name = "AccountID", nullable = false)
    private Account account;  // một tài khoản có nhiều lịch sử tải xuống
}