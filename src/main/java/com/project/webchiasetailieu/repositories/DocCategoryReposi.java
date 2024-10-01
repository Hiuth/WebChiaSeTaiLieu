package com.project.webchiasetailieu.repositories;

import com.project.webchiasetailieu.models.entites.DocCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// extends là kế thừa. Hàm này đang kế thừa từ Jpa, trong jpa thì sẽ có nhiều câu lệnh thay thế cho những câu lệnh query database thông thường
@Repository
public interface DocCategoryReposi extends JpaRepository<DocCategory, Integer> {
    DocCategory findByDocCategoryName(String docCategoryName);

    @Query("SELECT c FROM DocCategory c WHERE c.docCategoryName LIKE %:keyword%")
    List<DocCategory> findDocCategoryByKeyword(@Param("keyword") String keyword);
}
