package com.project.webchiasetailieu.controllers;

import com.project.webchiasetailieu.models.dtos.CommentDTO;
import com.project.webchiasetailieu.models.entites.Comment;
import com.project.webchiasetailieu.models.entites.DocCategory;
import com.project.webchiasetailieu.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @GetMapping("/{AccountId}")
    public ResponseEntity<List<Comment>> getAllCommentOfAccount(@PathVariable int AccountId) {
        List<Comment> accountComment = commentService.getAllCommentsOfAccount(AccountId);
        return ResponseEntity.ok(accountComment);
    }

    @PostMapping("")
    public ResponseEntity<?> createComment(@RequestBody CommentDTO commentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //kiểm tra lỗi
            List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errors.toString());
        }
        commentService.addComment(commentDTO);
        return ResponseEntity.ok("Added comment successfully");
    }

    @PutMapping("{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable int commentId, @RequestBody CommentDTO commentDTO) {
        commentService.updateComment(commentId, commentDTO);
        return ResponseEntity.ok("Comment updated successfully");
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable int commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }

}
