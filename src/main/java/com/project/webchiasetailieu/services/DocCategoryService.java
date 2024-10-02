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
        DocCategory docCategory = DocCategory.builder().docCategoryName(docCategoryDTO.getDocCategoryName()).build(); //tạo ra 1 đối tưởng rỗng rồi gán cái name vào
        return docCategoryReposi.save(docCategory);
    }

    @Override
    public DocCategory getDocCategoryById(int id) {
        return docCategoryReposi.findById(id)
                .orElseThrow(()-> new RuntimeException("docCategory not found"));
    }

    @Override
    public DocCategory updateDocCategory(int docCategoryId,  DocCategoryDTO docCategoryDTO) {
        DocCategory existingDocCategory= getDocCategoryById(docCategoryId);
        existingDocCategory.setDocCategoryName(docCategoryDTO.getDocCategoryName());
        docCategoryReposi.save(existingDocCategory);
        return existingDocCategory;
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
