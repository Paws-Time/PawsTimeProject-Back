package com.pawstime.pawstime.domain.post.entity.repository;

import com.pawstime.pawstime.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {


        // 게시글 조회 (기본 정렬은 작성일 기준 최신순, isDelete가 false인 게시글만 조회)
        @Query("SELECT p FROM Post p WHERE p.isDelete = false ORDER BY p.createdAt DESC")
        Page<Post> findAllActivePosts(Pageable pageable);

        // 특정 게시판에서 게시글 조회 (isDelete가 false인 게시글만 조회)
        @Query("SELECT p FROM Post p WHERE p.isDelete = false AND p.board.boardId = :boardId ORDER BY p.createdAt DESC")
        Page<Post> findByBoardIdAndActive(Long boardId, Pageable pageable);

        // 제목 또는 내용으로 검색 (isDelete가 false인 게시글만 조회)
        @Query("SELECT p FROM Post p WHERE p.isDelete = false " +
                "AND (LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
                "AND (:boardId IS NULL OR p.board.boardId = :boardId) ORDER BY p.createdAt DESC")
        Page<Post> findByKeywordAndBoardId(String keyword, Long boardId, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.isDelete = false " +
            "AND (:boardId IS NULL OR p.board.boardId = :boardId) " +
            "AND (:keyword IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "ORDER BY " +
            "CASE WHEN :sort = 'createdAt' THEN p.createdAt " +
            "     WHEN :sort = 'views' THEN p.views " +
            "     WHEN :sort = 'title' THEN p.title " +
            "     ELSE p.createdAt END DESC")
    Page<Post> findByBoardIdAndKeywordAndIsDeleted(Long boardId, String keyword, String sort, Pageable pageable);





}


