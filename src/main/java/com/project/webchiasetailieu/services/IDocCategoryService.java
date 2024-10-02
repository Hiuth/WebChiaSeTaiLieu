package com.project.webchiasetailieu.services;

import com.project.webchiasetailieu.models.dtos.DocCategoryDTO;
import com.project.webchiasetailieu.models.entites.DocCategory;

import java.util.List;

public interface IDocCategoryService {
    DocCategory createDocCategory(DocCategoryDTO docCategory);

    DocCategory updateDocCategory(int docCategoryId, DocCategoryDTO docCategory);

    void deleteDocCategory(int id);

    List<DocCategory> getAllDocCategory();

    DocCategory getDocCategoryById(int id);

}
