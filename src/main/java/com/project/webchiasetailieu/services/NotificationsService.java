package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.dtos.NotificationsDTO;
import com.project.webchiasetailieu.models.entites.Account;
import com.project.webchiasetailieu.models.entites.Notifications;
import com.project.webchiasetailieu.repositories.AccountReposi;
import com.project.webchiasetailieu.repositories.NotificationsReposi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationsService implements INotificationsService {

    private final NotificationsReposi notificationsReposi;
    private final AccountReposi accountReposi;
    @Override
    public Notifications createNotification(NotificationsDTO notificationDTO) {

        Account account = accountReposi.findById(notificationDTO.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Notifications notifications = Notifications.builder()
                .account(account)
                .title(notificationDTO.getTitle())
                .content(notificationDTO.getContent())
                .notiType(notificationDTO.getNotiType())
                .build();
        return notificationsReposi.save(notifications);
    }

    @Override
    public Notifications updateNotification(int notiId, NotificationsDTO notification) {
        Notifications existingNotification = getNotificationsById(notiId);
        return null;
    }

    @Override
    public Notifications deleteNotification(int notiId) {
        return null;
    }

    @Override
    public List<NotificationsDTO> getAllNotifications() {
        return List.of();
    }

    @Override
    public List<NotificationsDTO> getNotificationsByAccountId(int accountId) {
        return List.of();
    }

    @Override
    public Notifications getNotificationsById(int notifiId) {
        return notificationsReposi.findById(notifiId)
                .orElseThrow(()-> new RuntimeException("Notification not found"));
    }
}
