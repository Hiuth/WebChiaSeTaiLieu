
package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.entites.Account;
import com.project.webchiasetailieu.models.entites.PerInfo;
import com.project.webchiasetailieu.repositories.AccountReposi;
import com.project.webchiasetailieu.repositories.PerInfoReposi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.nio.file.NoSuchFileException;

@Service
public class PerInfoService implements IPerInfoService {

    @Autowired
    private PerInfoReposi perInfoReposi;

    @Autowired
    private AccountReposi accountReposi;

    private final String UPLOAD_DIR = "Pictures/";

    @Override
    public PerInfo createPerInfo(PerInfo perInfo) {
        // Fetch the existing account from the database
        Optional<Account> accountOptional = accountReposi.findById(perInfo.getAccount().getAccountId());
        if (accountOptional.isPresent()) {
            perInfo.setAccount(accountOptional.get());
        } else {
            throw new IllegalArgumentException("Account not found");
        }
        return perInfoReposi.save(perInfo);
    }

    @Override
    public List<PerInfo> findAllPerInfo() {
        return perInfoReposi.findAll();
    }

    @Override
    public PerInfo findPerInfoById(Integer id) {
        Optional<PerInfo> perInfo = perInfoReposi.findById(id);
        return perInfo.orElse(null);
    }

    @Override
    public PerInfo updatePerInfo(Integer id, PerInfo perInfoDetails) {
        PerInfo perInfo = findPerInfoById(id);
        if (perInfo == null) {
            throw new IllegalArgumentException("PerInfo not found");
        }
        perInfo.setFullName(perInfoDetails.getFullName());
        perInfo.setBirthday(perInfoDetails.getBirthday());
        perInfo.setSex(perInfoDetails.getSex());
        perInfo.setAvatar(perInfoDetails.getAvatar());
        perInfo.setAccount(perInfoDetails.getAccount());
        return perInfoReposi.save(perInfo);
    }

    @Override
    public void deletePerInfo(Integer id) {
        PerInfo perInfo = findPerInfoById(id);
        if (perInfo == null) {
            throw new IllegalArgumentException("PerInfo not found");
        }
        perInfoReposi.delete(perInfo);
    }

    @Override
    public String uploadAvatar(Integer id, MultipartFile file) throws IOException {
        // Ensure the uploads directory exists at the start of the method
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        PerInfo perInfo = findPerInfoById(id);
        if (perInfo == null) {
            throw new IllegalArgumentException("PerInfo not found");
        }

        // Save the file
        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        // Update the avatar field
        perInfo.setAvatar(fileName);
        perInfoReposi.save(perInfo);

        return fileName;
    }


    @Override
    public void deleteAvatar(Integer id) throws IOException {
        PerInfo perInfo = findPerInfoById(id);
        if (perInfo == null) {
            throw new IllegalArgumentException("PerInfo not found");
        }

        String avatarFileName = perInfo.getAvatar();
        if (avatarFileName != null && !avatarFileName.equals("default-avatar.jpg")) {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(avatarFileName);
            try {
                Files.delete(filePath);
            } catch (NoSuchFileException e) {
                throw new IllegalArgumentException("File not found: " + avatarFileName);
            }
        }

        // Optionally, reset the avatar field to the default value
        perInfo.setAvatar("default-avatar.jpg");
        perInfoReposi.save(perInfo);
    }
}
