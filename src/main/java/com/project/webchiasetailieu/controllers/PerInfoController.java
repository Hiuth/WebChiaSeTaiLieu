package com.project.webchiasetailieu.controllers;

import com.project.webchiasetailieu.models.entites.PerInfo;
import com.project.webchiasetailieu.services.IPerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/perinfo")
public class PerInfoController {

    @Autowired
    private IPerInfoService perInfoService;

    @PostMapping()
    public ResponseEntity<String> createPerInfo(@RequestBody PerInfo perInfo) {
        try {
            if (perInfo.getAvatar() == null || perInfo.getAvatar().isEmpty()) {
                perInfo.setAvatar("default-avatar.jpg"); // Set default value if not provided
            }
            PerInfo createdPerInfo = perInfoService.createPerInfo(perInfo);
            return ResponseEntity.status(201).body("PerInfo created successfully with ID: " + createdPerInfo.getPerInfoId());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }

    @PostMapping("/upload-avatar/{id}")
    public ResponseEntity<Resource> uploadAvatar(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        try {
            String avatarUrl = perInfoService.uploadAvatar(id, file);
            Path filePath = Paths.get("uploads/").resolve(avatarUrl).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(404).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping()
    public List<PerInfo> findAllPerInfo() {
        return perInfoService.findAllPerInfo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPerInfoById(@PathVariable Integer id) {
        try {
            PerInfo perInfo = perInfoService.findPerInfoById(id);
            if (perInfo != null) {
                return ResponseEntity.ok(perInfo);
            } else {
                return ResponseEntity.status(404).body("PerInfo not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePerInfo(@PathVariable Integer id, @RequestBody PerInfo perInfoDetails) {
        try {
            PerInfo updatedPerInfo = perInfoService.updatePerInfo(id, perInfoDetails);
            return ResponseEntity.ok("PerInfo updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("PerInfo not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerInfo(@PathVariable Integer id) {
        try {
            perInfoService.deletePerInfo(id);
            return ResponseEntity.ok("PerInfo deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("PerInfo not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }
    @DeleteMapping("/delete-avatar/{id}")
    public ResponseEntity<String> deleteAvatar(@PathVariable Integer id) {
        try {
            perInfoService.deleteAvatar(id);
            return ResponseEntity.ok("Avatar deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }
}