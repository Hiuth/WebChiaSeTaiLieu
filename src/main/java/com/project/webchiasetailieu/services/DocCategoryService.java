package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.dtos.DocCategoryDTO;
import com.project.webchiasetailieu.models.entites.DocCategory;
import com.project.webchiasetailieu.repositories.DocCategoryReposi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocCategoryService implements IDocCategoryService {
    private final DocCategoryReposi docCategoryReposi;

//    public DocCategoryService(DocCategoryReposi docCategoryReposi) {
//        this.docCategoryReposi = docCategoryReposi;
//    }

    @Override
    public DocCategory createDocCategory(DocCategoryDTO docCategoryDTO) {
        DocCategory docCategory = DocCategory.builder()
                .docCategoryFolder(docCategoryDTO.getDocCategoryFolder())
                .docCategoryName(docCategoryDTO.getDocCategoryName())
                .build(); //tạo ra 1 đối tưởng rỗng rồi gán cái name vào
        return docCategoryReposi.save(docCategory);
    }

    @Override
    public DocCategory getDocCategoryById(int id) {
        return docCategoryReposi.findById(id)
                .orElseThrow(()-> new RuntimeException("docCategory not found"));
    }

    @Override
    public List<DocCategory> getDocCategoryByNameFolder(String docCategoryFolder) {
        List<DocCategory> docCategoryList = docCategoryReposi.findByDocCategoryFolder(docCategoryFolder);
        if (docCategoryList.isEmpty()) {
            throw new RuntimeException("docCategory not found");
        }
        return docCategoryList;
    }


    @Override
    public DocCategory updateDocCategory(int docCategoryId,  DocCategoryDTO docCategoryDTO) {
        DocCategory existingDocCategory= getDocCategoryById(docCategoryId);
        existingDocCategory.setDocCategoryName(docCategoryDTO.getDocCategoryName());
        docCategoryReposi.save(existingDocCategory);
        return existingDocCategory;
    }

    @Override
    public List<DocCategory> updateDocCategoryFolder(String docCategoryFolder, DocCategoryDTO docCategoryDTO) {
        List<DocCategory> existingDocCategoryFolder = getDocCategoryByNameFolder(docCategoryFolder);
        for (DocCategory docCategory : existingDocCategoryFolder) {
            docCategory.setDocCategoryFolder(docCategoryDTO.getDocCategoryFolder());
            docCategoryReposi.save(docCategory);
        }
        return existingDocCategoryFolder;
    }

    @Override
    public DocCategory getDocCategoryIdByName(String docCategoryName) {
        return docCategoryReposi.findIdByDocCategoryName(docCategoryName)
                .orElseThrow(()-> new RuntimeException("docCategoryName not found"));
    }


    @Override
    public void deleteDocCategory(int id) {
         docCategoryReposi.deleteById(id);
    }

    @Override
    public List<DocCategory> getAllDocCategory() {
        return docCategoryReposi.findAll();
    }

}
