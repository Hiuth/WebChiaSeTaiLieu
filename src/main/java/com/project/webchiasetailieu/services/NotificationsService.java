package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.dtos.NotificationsDTO;
import com.project.webchiasetailieu.models.entites.Notifications;

import java.util.List;

public class NotificationsService implements INotificationsService {

    @Override
    public Notifications createNotification(NotificationsDTO notification) {
        return null;
    }

    @Override
    public Notifications updateNotification(int notiId, NotificationsDTO notification) {
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
}
