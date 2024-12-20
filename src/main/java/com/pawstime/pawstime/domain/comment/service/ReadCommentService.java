package com.pawstime.pawstime.domain.comment.service;

import com.pawstime.pawstime.domain.comment.entity.repository.CommentRepository;
import com.pawstime.pawstime.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadCommentService {

  private final CommentRepository commentRepository;

  public Post findByIdQuery(Long postId) {
    return commentRepository.findByIdQuery(postId);
  }
}
