package com.project.webchiasetailieu.repositories;

import com.project.webchiasetailieu.models.entites.DocCategory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// extends là kế thừa. Hàm này đang kế thừa từ Jpa, trong jpa thì sẽ có nhiều câu lệnh thay thế cho những câu lệnh query database thông thường
@Repository
public interface DocCategoryReposi extends JpaRepository<DocCategory, Integer> {
    @Query("select  docCate from DocCategory docCate where docCate.docCategoryFolder =:DocCategoryFolder")
    List<DocCategory> findByDocCategoryFolder(@Param("DocCategoryFolder") String DocCategoryFolder);

    @Query("SELECT c FROM DocCategory c WHERE c.docCategoryName LIKE %:keyword%")
    List<DocCategory> findDocCategoryByKeyword(@Param("keyword") String keyword);

    @Query("select  docCate from DocCategory docCate where docCate.docCategoryName =:DocCategoryName")
    Optional<DocCategory> findIdByDocCategoryName(@Param("DocCategoryName") String DocCategoryName);

    @Transactional
    @Modifying
    @Query("delete from DocCategory docCate where docCate.docCategoryFolder = :DocCategoryFolder ")
    void deleteByDocCategoryFolder(@Param("DocCategoryFolder") String DocCategoryFolder);

}
