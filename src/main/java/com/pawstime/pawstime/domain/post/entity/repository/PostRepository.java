package com.pawstime.pawstime.domain.post.entity.repository;

import com.pawstime.pawstime.domain.post.entity.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
///*
//    @Query("SELECT p FROM Post p WHERE p.isDelete = false " +
//            "AND (:keyword IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
//            "OR LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
//            "AND (:boardId IS NULL OR p.board.id = :boardId) " +
//            "ORDER BY " +
//            "CASE WHEN :sort = 'createdAt' THEN p.createdAt " +
//            "     WHEN :sort = 'views' THEN p.views " +
//            "     WHEN :sort = 'title' THEN p.title " +
//            "     ELSE p.createdAt END DESC")
//    Page<Post> findByBoardIdAndKeywordAndIsDeleted(Long boardId, String keyword, boolean isDeleted, Pageable pageable);
//*/

}




