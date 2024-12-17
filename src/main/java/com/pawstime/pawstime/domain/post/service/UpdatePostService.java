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

  public void updatePost(Post post, UpdatePostReqDto req){

    //기존 게시글 엔티티에 새 값 적용
    post.setTitle(req.title());
    post.setContent(req.content());
    post.setCategory(req.postCategory());

    postRepository.save(post);
  }
}
