package com.project.webchiasetailieu.controllers;

import com.project.webchiasetailieu.models.dtos.DownloadDTO;
import com.project.webchiasetailieu.models.entites.Download;
import com.project.webchiasetailieu.services.DownloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/download")
@RequiredArgsConstructor
public class DownloadController {
    private final DownloadService downloadService;

    @GetMapping()
    public ResponseEntity<List<Download>> getAllDownloads() {
        List<Download> download = downloadService.getAllDownload();
        return ResponseEntity.ok(download);
    }
    @GetMapping("/DowHis/{AccountId}")
    public ResponseEntity<List<Download>> getDownloadByAccountId(@PathVariable int AccountId) {
        List<Download> download = downloadService.getAllDownloadFromAccount(AccountId);
        return ResponseEntity.ok(download);
    }

    @PostMapping()
    public ResponseEntity<?> createDownload(@RequestBody DownloadDTO downloadDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errors.toString());
        }
        Download download = downloadService.createDownload(downloadDTO);
        return ResponseEntity.ok(download);
    }
}


