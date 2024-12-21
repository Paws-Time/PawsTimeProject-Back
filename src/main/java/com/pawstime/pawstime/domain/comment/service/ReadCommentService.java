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

  public Post findByIdQuery(Long postId) {
    return commentRepository.findByIdQuery(postId);
  }

  public Page<Comment> getCommentAll(Pageable pageable) {
    return commentRepository.findAllQuery(pageable);
  }
}
