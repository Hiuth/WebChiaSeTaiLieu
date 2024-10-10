package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.dtos.NotificationsDTO;
import com.project.webchiasetailieu.models.entites.Notifications;

import java.util.List;

public interface INotificationsService {
    Notifications createNotification(NotificationsDTO notification);

    Notifications updateNotification(int notiId,NotificationsDTO notification);

    void deleteNotification(int notiId);

    List<Notifications> getAllNotifications();

    List<Notifications> getNotificationsByAccountId(int accountId);

    Notifications getNotificationsById(int notifiId);
}
