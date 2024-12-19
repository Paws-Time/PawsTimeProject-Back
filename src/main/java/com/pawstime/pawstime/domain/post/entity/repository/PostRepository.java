package com.pawstime.pawstime.domain.post.entity.repository;

import com.pawstime.pawstime.domain.post.entity.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 모든 게시판의 모든 게시글 조회 (삭제되지 않은 게시글만 조회)
    Page<Post> findByIsDeleteFalse(Pageable pageable);

    // 특정 게시판의 게시글 조회 (삭제되지 않은 게시글만)
    Page<Post> findByBoard_boardIdAndIsDeleteFalse(Long boardId, Pageable pageable);

    // 제목과 내용에서 검색 (삭제되지 않은 게시글만)
    Page<Post> findByTitleContainingOrContentContainingAndIsDeleteFalse(String titleKeyword, String contentKeyword, Pageable pageable);

    // 제목과 내용에서 검색 (특정 게시판, 삭제되지 않은 게시글만)
    Page<Post> findByTitleContainingOrContentContainingAndBoard_boardIdAndIsDeleteFalse(
            String titleKeyword, String contentKeyword, Long boardId, Pageable pageable);

    // 제목과 내용에서 검색 (특정 게시판, 삭제되지 않은 게시글만) - 수정
    @Query("SELECT p FROM Post p WHERE p.board.boardId = :boardId AND p.isDelete = false " +
            "AND (p.title LIKE %:titleKeyword% OR p.content LIKE %:contentKeyword%)")
    Page<Post> findByBoardIdAndIsDeleteFalseAndTitleContainingOrContentContaining(
            @Param("boardId") Long boardId,
            @Param("titleKeyword") String titleKeyword,
            @Param("contentKeyword") String contentKeyword,
            Pageable pageable);

    // 최신순 (작성일 기준, 삭제되지 않은 게시글만)
    Page<Post> findAllByIsDeleteFalseOrderByCreatedAtDesc(Pageable pageable);

    // 조회수 기준 내림차순 (삭제되지 않은 게시글만)
    Page<Post> findAllByIsDeleteFalseOrderByViewsDesc(Pageable pageable);

    // 가나다순 (제목 기준, 삭제되지 않은 게시글만)
    Page<Post> findAllByIsDeleteFalseOrderByTitleAsc(Pageable pageable);

    // 제목으로 게시글 조회 (삭제된 게시글 제외)
    Post findByTitle(String title);

    // 특정 게시글 조회 (삭제되지 않은 게시글만)
    // 특정 게시글 조회 (삭제되지 않은 게시글만)
    Optional<Post> findByPostIdAndIsDeleteFalse(Long postId);

}
