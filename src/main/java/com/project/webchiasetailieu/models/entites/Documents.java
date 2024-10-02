package com.project.webchiasetailieu.models.entites;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity //xác định thực thể trong jpa
@Table(name = "Documents") // nếu khác ten so với trong database thì hẳn xài
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DocID", nullable = false)
    private int docId;

    @Column(name="DocName", nullable = false, length = 255)
    private String docName;

    @Column(name ="DocType", nullable = false,length = 50)
    private String docType;

    @Column(name ="DocBinary", nullable = false)
    private byte[] docBinary;

    @Column(name ="Description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name="IsPaid", nullable = false)
    private boolean isPaid;

    @Column(name="Point", nullable = false)
    private int point;

    @ManyToOne// một tài khoản được phép đăng nhiều tài liệu
    @JoinColumn(name="AccountID", nullable = false)
    private Account account;

    @ManyToOne//một danh mục có thể có nhiều tài liệu
    @JoinColumn (name="DocCategoryID", nullable = false) //1 danh mục thì được sử dụng bởi nhiều bài đăng tài liệu khác nhau
    private DocCategory docCategory;

    @Column(name="CreateDay", nullable = false)
    private LocalDateTime createDay; //tạo tự động

    @Column(name="DowTime", nullable = false)
    private int dowTime;

    @PrePersist
    protected void onCreate() {
        createDay = LocalDateTime.now();
    }

}

