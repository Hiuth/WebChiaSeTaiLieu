package com.project.webchiasetailieu.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //hàm khởi tạo constructor của class
@NoArgsConstructor
public class NotificationsDTO {
    private int notiId;
    private  int accountId; // account nhan thong bao
    private String title; // tieu de;
    private String content; //noi dung
    private String notiType;
}
