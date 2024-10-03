package com.project.webchiasetailieu.services;


import com.project.webchiasetailieu.models.dtos.CommentDTO;
import com.project.webchiasetailieu.models.entites.Account;
import com.project.webchiasetailieu.models.entites.Comment;
import com.project.webchiasetailieu.models.entites.Documents;
import com.project.webchiasetailieu.repositories.AccountReposi;
import com.project.webchiasetailieu.repositories.CommentsReposi;
import com.project.webchiasetailieu.repositories.DocCategoryReposi;
import com.project.webchiasetailieu.repositories.DocumentsReposi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {
    private final CommentsReposi commentsReposi;
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
        return commentsReposi.save(comment);
    }

    @Override
    public Comment updateComment(int commentId,CommentDTO commentDTO) {
        Comment exisitingComment = getCommentById(commentId);
        exisitingComment.setComText(commentDTO.getCommentText());
        commentsReposi.save(exisitingComment);
        return exisitingComment;
    }

    @Override
    public void deleteComment(int commentId) {
        commentsReposi.deleteById(commentId);
    }

    @Override
    public Comment getCommentById(int id) {
        return commentsReposi.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }
}
