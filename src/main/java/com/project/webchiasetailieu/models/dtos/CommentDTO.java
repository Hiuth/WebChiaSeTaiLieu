package com.project.webchiasetailieu.models.dtos;

import com.project.webchiasetailieu.models.entites.Account;
import com.project.webchiasetailieu.models.entites.Documents;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private int commentId;
    private String commentText;
    private int accountId;
    private int documentId;
}
