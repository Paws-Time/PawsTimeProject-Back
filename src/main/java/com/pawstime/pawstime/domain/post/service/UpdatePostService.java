package com.pawstime.pawstime.domain.post.service;

import com.pawstime.pawstime.domain.post.dto.req.UpdatePostReqDto;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePostService {

  private final PostRepository postRepository;

  // 게시글 수정 및 저장
  public void updatePost(Post post, UpdatePostReqDto req) {
    post.setTitle(req.title());
    post.setContent(req.content());

    postRepository.save(post);
  }
}
