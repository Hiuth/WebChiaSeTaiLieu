package com.project.webchiasetailieu.repositories;

import com.project.webchiasetailieu.models.entites.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationsReposi extends JpaRepository<Notifications, Integer> {
    List<Notifications> findAllByAccount_AccountId(int account_id);
}
