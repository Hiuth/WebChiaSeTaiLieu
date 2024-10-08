package com.project.webchiasetailieu.controllers;

import com.project.webchiasetailieu.models.entites.Documents;
import com.project.webchiasetailieu.services.DocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentsController {

    @Autowired
    private DocumentsService documentService;

    @PostMapping("/upload")
    public ResponseEntity<Documents> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("docName") String docName,
            @RequestParam("docType") String docType,
            @RequestParam("description") String description,
            @RequestParam("docCategoryId") int docCategoryId,
            @RequestParam("isPaid") boolean isPaid,
            @RequestParam("point") int point,
            @RequestParam("accountId") int accountId) throws IOException {
        Documents document = documentService.uploadDocument(file, docName, docType, description, docCategoryId, isPaid, point, accountId);
        return ResponseEntity.ok(document);
    }

    @GetMapping
    public ResponseEntity<List<Documents>> getAllDocuments() {
        List<Documents> documents = documentService.getAllDocuments();
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documents> getDocumentById(@PathVariable int id) {
        Documents document = documentService.getDocumentById(id);
        return ResponseEntity.ok(document);
    }
}