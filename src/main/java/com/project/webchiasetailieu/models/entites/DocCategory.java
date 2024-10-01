package com.project.webchiasetailieu.models.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity //xác định thực thể trong jpa
@Table(name = "DocCategory") // nếu khác ten so với trong database thì hẳn xài
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocCategory {
    @Id // dùng để xác định thằng đầu tiên là khoá chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)// tăng tự động
    @Column(name="DocCategoryID")
    private int  docCategoryId;

    @Column(name="DocCategoryName", nullable=false)
    private String docCategoryName;
}
