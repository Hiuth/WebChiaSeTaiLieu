package com.project.webchiasetailieu.models.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity //xác định thực thể trong jpa
@Table(name = "DocCategory")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocCategory {
    @Id // dùng để xác định thằng đầu tiên là khoá chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)// tăng tự động
    @Column(name="DocCategoryID")
    private int  docCategoryId;

    @Column(name="DocCategoryFolder",nullable = false)
    private String docCategoryFolder;

    @Column(name="DocCategoryName", nullable=false,unique = true)
    private String docCategoryName;
}
