package com.pawstime.pawstime.domain.comment.service;

import com.pawstime.pawstime.domain.comment.entity.Comment;
import com.pawstime.pawstime.domain.comment.entity.repository.CommentRepository;
import com.pawstime.pawstime.domain.post.entity.Post;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadCommentService {

  private final CommentRepository commentRepository;

  public Post getPostById(Long postId) {
    return commentRepository.findPostByIdQuery(postId);
  }

  public Page<Comment> getCommentAll(Pageable pageable) {
    return commentRepository.findAllQuery(pageable);
  }

  public Page<Comment> getCommentByPost(Long postId, Pageable pageable) {
    return commentRepository.findAllByPostQuery(postId, pageable);
  }

  public Comment findById(Long commentId) {
    return commentRepository.findById(commentId).orElse(null);
  }
}
