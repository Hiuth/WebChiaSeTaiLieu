package com.project.webchiasetailieu.controllers;

import com.project.webchiasetailieu.models.dtos.NotificationsDTO;
import com.project.webchiasetailieu.models.entites.Notifications;
import com.project.webchiasetailieu.repositories.NotificationsReposi;
import com.project.webchiasetailieu.services.NotificationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notification")
@RequiredArgsConstructor
public class NotificationsController {
    private final NotificationsService notificationsService;
    private final NotificationsReposi notificationsReposi;

    @GetMapping("/accNoti/{accountId}")
    public ResponseEntity<List<Notifications>> getAllNotificationsOfAccount(@PathVariable int accountId) {
        List<Notifications> notifications = notificationsService.getNotificationsByAccountId(accountId);
        return ResponseEntity.ok(notifications);
    }

    @PostMapping("")
    public ResponseEntity<?> createNotification(@RequestBody NotificationsDTO notificationsDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errors.toString());
        }
        notificationsService.createNotification(notificationsDTO);
        return ResponseEntity.ok("Create notification successful");
    }

    @DeleteMapping("/delete/{NotiId}")
    public ResponseEntity<String> deleteNotification(@PathVariable int NotiId) {
        notificationsService.deleteNotification(NotiId);
        return ResponseEntity.ok("Delete notification successful");
    }
}
