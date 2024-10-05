package com.project.webchiasetailieu.repositories;

import jakarta.transaction.Transactional;
import com.project.webchiasetailieu.models.entites.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentReposi extends JpaRepository<Comment, Integer> {
    @Modifying // dùng cho những hàm thay đổi dữ liệu
    @Transactional
    @Query("delete from Comment  c where c.comID = :commentId")
    void deleteCommentById(@Param("commentId") int commentId);

//    Comment findCommentByCommentId(int id);
    @Query("select com from Comment com where com.account.accountId = :accountId")
    List<Comment> findCommentByAccount_AccountId(@Param("accountId") int accountId);


    @Modifying
    @Transactional
    @Query("update Comment com set com.comText = :comText where com.comID =:comID" )
    void updateComTextById(@Param("comID") int comID, @Param("comText") String comText);
}
