package com.project.webchiasetailieu.models.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Download {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DowID")
    private int dowID;

    @Column(name = "Point", nullable = false)
    private int point;

    @Column(name="Date", nullable = false)
    private Date date;

    @ManyToOne // một tài khoản được mua nhiều tài liệu
    @JoinColumn(name = "AccountID", nullable = false)
    private Account account;

    @OneToOne // mỗi lần mua tài liệu thì chỉ được mua 1 tài liệu
    @JoinColumn (name="DocID",nullable = false)
    private Documents documents;

    @PrePersist
    protected void onCreate() {
        this.date = new Date(); // Chỉ gán lần đầu khi thực thể được tạo mới
    }
}
