package com.pawstime.pawstime.domain.post.entity.repository;

import com.pawstime.pawstime.domain.post.entity.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.isDelete = false " +
            "AND (:boardId IS NULL OR p.board.boardId = :boardId) " +
            "AND (:keyword IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "ORDER BY " +
            "CASE " +
            "   WHEN :sort = 'createdAt' THEN p.createdAt " +
            "   WHEN :sort = 'views' THEN p.views " +
            "   WHEN :sort = 'title' THEN p.title " +
            "   ELSE p.createdAt " +
            "END " +
            "ASC")

    Page<Post> findByBoardIdAndKeywordAndIsDeleted(Long boardId, String keyword, String sort, Pageable pageable);

}


