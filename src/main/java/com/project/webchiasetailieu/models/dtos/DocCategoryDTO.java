package com.project.webchiasetailieu.models.dtos;

import lombok.*;

@Data
@AllArgsConstructor //hàm khởi tạo constructor của class
@NoArgsConstructor //khởi tạo mặc định không có tham số
public class DocCategoryDTO {
    private int docCategoryId;
    private String docCategoryName;
}
