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
                .orElse(null);;

        return GetDetailPostRespDto.from(post);  // DTO 반환

    }
}
