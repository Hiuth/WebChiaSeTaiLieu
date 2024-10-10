package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.dtos.NotificationsDTO;
import com.project.webchiasetailieu.models.entites.Notifications;

import java.util.List;

public interface INotificationsService {
    Notifications createNotification(NotificationsDTO notification);

    Notifications updateNotification(int notiId,NotificationsDTO notification);

    Notifications deleteNotification(int notiId);

    List<NotificationsDTO> getAllNotifications();

    List<NotificationsDTO> getNotificationsByAccountId(int accountId);

    Notifications getNotificationsById(int notifiId);
}
