package com.project.webchiasetailieu.repositories;

import com.project.webchiasetailieu.models.entites.Download;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DownloadReposi extends JpaRepository<Download, Integer> {
    List<Download> findAllDownloadByAccount_AccountId(int accountId);
}
