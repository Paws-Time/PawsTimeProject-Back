package com.pawstime.pawstime.domain.post.service;

import com.pawstime.pawstime.domain.comment.entity.repository.CommentRepository;
import com.pawstime.pawstime.domain.post.dto.resp.GetDetailPostRespDto;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetDetailPostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public GetDetailPostRespDto getDetailPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElse(null);

        post.increaseViews();
        postRepository.save(post); // 조회수가 증가된 게시글 저장


        long commentCount = commentRepository.countByPost(post);

        // DTO 반환 시 댓글 수 포함
        return GetDetailPostRespDto.from(post, commentCount);
    }
}
