package com.project.webchiasetailieu.controllers;

import com.project.webchiasetailieu.models.dtos.FeedBackDTO;
import com.project.webchiasetailieu.models.entites.FeedBack;
import com.project.webchiasetailieu.services.FeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/FeedBack")
@RequiredArgsConstructor
public class FeedBackController {
    private final FeedBackService feedBackService;

    @GetMapping("/all")
    public ResponseEntity<List<FeedBack>> getAllFeedBack() {
        List<FeedBack> feedBacks = feedBackService.getAllFeedBack();
        return ResponseEntity.ok(feedBacks);
    }

    @GetMapping("/accFeed/{accountId}")
    public ResponseEntity<List<FeedBack>> getAccountFeedBack(@PathVariable int accountId) {
        List<FeedBack> feedBacks = feedBackService.getAccountFeedBack(accountId);
        return ResponseEntity.ok(feedBacks);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> createFeedBack(@RequestBody FeedBackDTO feedBackDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //kiểm tra lỗi
            List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errors.toString());
        }
        feedBackService.createFeedBack(feedBackDTO);
        return ResponseEntity.ok("Create feedback successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFeedBack(@RequestBody FeedBackDTO feedBackDTO, @PathVariable int id) {

        feedBackService.updateFeedBack(id, feedBackDTO);
        return ResponseEntity.ok("Update feedback successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFeedBack(@PathVariable int id) {
        FeedBack feedBack = feedBackService.getFeedBackById(id);
        feedBackService.DeleteFeedBackById(id);
        return ResponseEntity.ok("Delete feedback successfully");
    }

}
