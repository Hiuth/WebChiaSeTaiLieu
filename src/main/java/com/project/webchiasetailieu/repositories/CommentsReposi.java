package com.project.webchiasetailieu.repositories;

import jakarta.transaction.Transactional;
import com.project.webchiasetailieu.models.entites.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentsReposi extends JpaRepository<Comment, Integer> {
    @Modifying
    @Transactional
    @Query("delete from Comment  c where c.comID = :commentId")
    void deleteCommentById(@Param("commentId") int commentId);

//    Comment findCommentByCommentId(int id);

    @Modifying
    @Transactional
    @Query("update Comment com set com.comText = :comText where com.comID =:comID" )
    void updateComTextById(@Param("comID") int comID, @Param("comText") String comText);
}
