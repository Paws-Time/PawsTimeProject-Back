package com.pawstime.pawstime.domain.post.service;

import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostService {

  private final PostRepository postRepository;

  public void createPost(Post post){

    //1.게시글 저장
    postRepository.save(post);

  }
}
