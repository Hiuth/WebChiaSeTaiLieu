package com.project.webchiasetailieu.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {
    private int documentId;
    private String documentName;
    private String documentType;
    private int documentSize;
    private byte docBinary;
    private String description;
    private int docCategoryId;
    private boolean isPaid;
    private int point;
    private int accountId;
    private LocalDateTime createDay;
    private String docAvatar;
    private int dowTime;
}