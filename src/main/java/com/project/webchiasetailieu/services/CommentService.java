package com.project.webchiasetailieu.services;


import com.project.webchiasetailieu.models.dtos.CommentDTO;
import com.project.webchiasetailieu.models.entites.Account;
import com.project.webchiasetailieu.models.entites.Comment;
import com.project.webchiasetailieu.models.entites.Documents;
import com.project.webchiasetailieu.repositories.AccountReposi;
import com.project.webchiasetailieu.repositories.CommentReposi;
import com.project.webchiasetailieu.repositories.DocumentsReposi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {
    private final CommentReposi commentReposi;
    private final AccountReposi accountReposi;
    private final DocumentsReposi documentsReposi;

    //thêm mới bình luận
    @Override
    public Comment addComment(CommentDTO commentDTO) {
        Account account = accountReposi.findById(commentDTO.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Documents documents = documentsReposi.findById(commentDTO.getDocumentId())
                .orElseThrow(() -> new RuntimeException("Document not found"));

        Comment comment = Comment.builder()
                .account(account)
                .document(documents)
                .comText(commentDTO.getCommentText())
                .build();
        return commentReposi.save(comment);
    }

    @Override
    public Comment updateComment(int commentId,CommentDTO commentDTO) {
        Comment exisitingComment = getCommentById(commentId);
        exisitingComment.setComText(commentDTO.getCommentText());
        commentReposi.save(exisitingComment);
        return exisitingComment;
    }

    @Override
    public void deleteComment(int commentId) {
        commentReposi.deleteById(commentId);
    }

    @Override
    public Comment getCommentById(int id) {
        return commentReposi.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    @Override
    public List<Comment> getAllCommentsOfAccount(int accountId) {
        return commentReposi.findCommentByAccount_AccountId(accountId);
    }


}