package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.dtos.CommentDTO;
import com.project.webchiasetailieu.models.entites.Comment;

import java.util.List;

public interface ICommentService {
    Comment addComment(CommentDTO comment);

    Comment updateComment(int commentId,CommentDTO comment);

    void deleteComment(int commentId);

    Comment getCommentById(int id);

    List<Comment> getAllCommentsOfAccount(int accountId);
}