package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.dtos.CommentDTO;
import com.project.webchiasetailieu.models.entites.Comment;

public interface ICommentService {
    Comment addComment(CommentDTO comment);

    Comment updateComment(CommentDTO comment);

    void deleteComment(CommentDTO comment);

    Comment getCommentById(int id);
}
