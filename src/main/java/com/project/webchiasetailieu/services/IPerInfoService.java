package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.entites.PerInfo;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import java.util.List;

public interface IPerInfoService {
    PerInfo createPerInfo(PerInfo perInfo);
    String uploadAvatar(Integer id, MultipartFile file) throws Exception;
    List<PerInfo> findAllPerInfo();
    PerInfo findPerInfoById(Integer id);
    PerInfo updatePerInfo(Integer id, PerInfo perInfoDetails);
    void deletePerInfo(Integer id);
    void deleteAvatar(Integer id) throws IOException;
}