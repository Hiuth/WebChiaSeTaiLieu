package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.entites.Documents;
import com.project.webchiasetailieu.models.dtos.DocumentDTO;
import com.project.webchiasetailieu.models.entites.DocCategory;
import com.project.webchiasetailieu.models.entites.Account;
import com.project.webchiasetailieu.repositories.DocumentsReposi;
import com.project.webchiasetailieu.repositories.DocCategoryReposi;
import com.project.webchiasetailieu.repositories.AccountReposi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DocumentsService implements IDocumentsService {

    @Autowired
    private DocumentsReposi documentRepository;

    @Autowired
    private DocCategoryReposi docCategoryReposi;

    @Autowired
    private AccountReposi accountReposi;

    @Override
    public Documents uploadDocument(MultipartFile file, String docName, String docType, String docUrl ,String description, int docCategoryId, int point, int accountId) throws IOException {
        DocCategory docCategory = docCategoryReposi.findById(docCategoryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        Account account = accountReposi.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));

        Documents document = Documents.builder()
                .docName(docName)
                .docType(docType)
                //.docBinary(file.getBytes())
                .docUrl(docUrl)
                .description(description)
                .docCategoryId(docCategory)
                //.isPaid(isPaid)
                .point(point)
                .accountId(account)
                .createDay(LocalDateTime.now())
                .dowTime(0)
                .build();
        return documentRepository.save(document);
    }

    @Override
    public List<Documents> getAllDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public Documents getDocumentById(int id) {
        return documentRepository.findById(id).orElse(null);
    }

    @Override
    public Documents updateDocument(int id, DocumentDTO documentDTO) {
        Documents document = getDocumentById(id);
        if (document == null) {
            throw new IllegalArgumentException("Document not found");
        }

        DocCategory docCategory = docCategoryReposi.findById(documentDTO.getDocCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        Account account = accountReposi.findById(documentDTO.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));

        document.setDocName(documentDTO.getDocumentName());
        document.setDocType(documentDTO.getDocumentType());
        document.setDescription(documentDTO.getDescription());
        document.setDocCategoryId(docCategory);
        //document.setPaid(documentDTO.isPaid());
        document.setPoint(documentDTO.getPoint());
        document.setAccountId(account);
        document.setDocAvatar(documentDTO.getDocAvatar());

        return documentRepository.save(document);
    }
    @Override
    public Documents deleteDocument(int id) {
        Documents document = getDocumentById(id);
        if (document == null) {
            throw new IllegalArgumentException("Document not found");
        }
        documentRepository.delete(document);
        return document;
    }
}