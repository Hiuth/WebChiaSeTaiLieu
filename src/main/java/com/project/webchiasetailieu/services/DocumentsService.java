package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.entites.Documents;
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
    public Documents uploadDocument(MultipartFile file, String docName, String docType, String description, int docCategoryId, boolean isPaid, int point, int accountId) throws IOException {
        DocCategory docCategory = docCategoryReposi.findById(docCategoryId).orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        Account account = accountReposi.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));

        Documents document = Documents.builder()
                .docName(docName)
                .docType(docType)
                .docBinary(file.getBytes())
                .description(description)
                .docCategoryId(docCategory)
                .isPaid(isPaid)
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
}