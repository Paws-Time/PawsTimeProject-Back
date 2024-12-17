package com.pawstime.pawstime.domain.post.service;

import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePostService {
  // PostRepository를 주입받습니다.
  private final PostRepository postRepository;

  // 포스트를 삭제하는 메서드

  public void deletePost(Long postId) {
    // 1. 포스트 조회
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("포스트가 존재하지 않습니다."));

    // 2. 이미 삭제된 포스트인 경우 예외 처리
    if (post.isDelete()) {
      throw new IllegalArgumentException("이미 삭제된 포스트입니다.");
    }

    // 3. 포스트를 소프트 딜리트 상태로 변경
    post.softDelete();  // Post 엔티티의 softDelete 메서드를 호출하여 삭제 처리

    // 4. 변경된 포스트를 저장
    postRepository.save(post);
  }

}
