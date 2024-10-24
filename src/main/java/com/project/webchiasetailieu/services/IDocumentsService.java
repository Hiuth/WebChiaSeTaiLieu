package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.dtos.DocumentDTO;
import com.project.webchiasetailieu.models.entites.Documents;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IDocumentsService {
    Documents uploadDocument(MultipartFile file, String docName, String docType, String docUrl,String description, int docCategoryId, int point, int accountId) throws IOException;
    List<Documents> getAllDocuments();
    Documents getDocumentById(int id);
    Documents updateDocument(int id, DocumentDTO documentDTO);
    Documents deleteDocument(int id);
}