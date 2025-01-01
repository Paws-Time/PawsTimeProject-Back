package com.pawstime.pawstime.domain.comment.entity.repository;

import com.pawstime.pawstime.domain.comment.entity.Comment;
import com.pawstime.pawstime.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  @Query("SELECT p FROM Post p WHERE p.postId = :postId AND p.isDelete = false")
  Post findPostByIdQuery(Long postId);

  @Query("SELECT c FROM Comment c WHERE c.isDelete = false")
  Page<Comment> findAllQuery(Pageable pageable);

  @Query("SELECT c FROM Comment c WHERE c.post.postId = :postId AND c.isDelete = false")
  Page<Comment> findAllByPostQuery(Long postId, Pageable pageable);

  Optional<Comment> findByPostAndContent(Post post, String content);
}
