package com.pawstime.pawstime.domain.comment.entity.repository;

import com.pawstime.pawstime.domain.comment.entity.Comment;
import com.pawstime.pawstime.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  @Query("SELECT p FROM Post p WHERE p.postId = :postId AND p.isDelete = false")
  Post findByIdQuery(Long postId);

  @Query("SELECT c FROM Comment c WHERE c.isDelete = false")
  Page<Comment> findAllQuery(Pageable pageable);
}
