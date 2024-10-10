package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.dtos.DownloadDTO;
import com.project.webchiasetailieu.models.entites.Download;

import java.util.List;

public interface IDownloadService {
    Download getDownload(int id);

    List<Download> getAllDownloadFromAccount(int accountId);

    Download createDownload(DownloadDTO downloadDTO);

    List<Download> getAllDownload();

    void createNotification1(int accountId);

    void createNotification2(int accountId,String DocName, int point);

    boolean checkDownloadBefore(int accountId, int documentId);

    Download updateDownload(int downloadId, DownloadDTO downloadDTO);

    void deleteDownload(int downloadId);
}
