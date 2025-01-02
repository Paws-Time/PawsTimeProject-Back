package com.pawstime.pawstime.domain.post.service;

import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostService {

  private final PostRepository postRepository;

  public Post createPost(Post post){

    return postRepository.save(post);

  }
}
