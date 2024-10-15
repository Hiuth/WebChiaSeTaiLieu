package com.project.webchiasetailieu.controllers;

import com.project.webchiasetailieu.models.dtos.DriveDTO;
import com.project.webchiasetailieu.models.dtos.DocumentDTO;
import com.project.webchiasetailieu.models.entites.Documents;
import com.project.webchiasetailieu.services.DocumentsService;
import com.project.webchiasetailieu.services.DriveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentsController {
    private static final Logger logger = LoggerFactory.getLogger(DocumentsController.class);

    @Autowired
    private DocumentsService documentService;

    @Autowired
    private DriveService ser;

    @PostMapping("/drive/upload")
    public Object handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException, GeneralSecurityException {
        if (file.isEmpty()) {
            return "File is empty";
        }
        File tempFile = File.createTempFile("file", null);
        file.transferTo(tempFile);
        DriveDTO res = ser.uploadImageToDrive(tempFile);
        System.out.println(res);
        return res;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("docName") String docName,
            @RequestParam("docType") String docType,
            @RequestParam("description") String description,
            @RequestParam("docCategoryId") int docCategoryId,
            @RequestParam("isPaid") boolean isPaid,
            @RequestParam("point") int point,
            @RequestParam("accountId") int accountId) throws IOException {
        try {
            Documents document = documentService.uploadDocument(file, docName, docType, description, docCategoryId, isPaid, point, accountId);
            return ResponseEntity.ok(document);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Documents>> getAllDocuments() {
        List<Documents> documents = documentService.getAllDocuments();
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documents> getDocumentById(@PathVariable int id) {
        logger.debug("Fetching document with ID: {}", id);
        Documents document = documentService.getDocumentById(id);
        if (document == null) {
            logger.debug("Document with ID: {} not found", id);
            return ResponseEntity.status(404).body(null);
        }
        logger.debug("Document with ID: {} found", id);
        return ResponseEntity.ok(document);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDocument(@PathVariable int id, @RequestBody DocumentDTO documentDTO) {
        try {
            Documents updatedDocument = documentService.updateDocument(id, documentDTO);
            return ResponseEntity.ok(updatedDocument);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable int id) {
        try {
            documentService.deleteDocument(id);
            return ResponseEntity.ok("Document deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}