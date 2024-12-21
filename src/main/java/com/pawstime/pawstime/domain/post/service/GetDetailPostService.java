package com.pawstime.pawstime.domain.post.service;

import com.pawstime.pawstime.domain.post.dto.resp.GetDetailPostRespDto;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetDetailPostService {
    private final PostRepository postRepository;

    public GetDetailPostRespDto getDetailPost(Long postId){
        Post post = postRepository.findById(postId)
                .orElse(null);
        // 조회수 증가
        post.increaseViews(); // 조회수 증가
        postRepository.save(post); // 조회수가 증가된 게시글 저장

        return GetDetailPostRespDto.from(post);  // DTO 반환

    }
}
