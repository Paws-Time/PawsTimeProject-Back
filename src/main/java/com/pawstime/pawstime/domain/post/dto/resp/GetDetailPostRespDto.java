package com.pawstime.pawstime.domain.post.dto.resp;

import com.pawstime.pawstime.domain.comment.entity.repository.CommentRepository;
import com.pawstime.pawstime.domain.post.entity.Post;
import java.time.LocalDateTime;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;

@Builder
public record GetDetailPostRespDto(
        Long bordId,
        Long postId,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long commentCount // 댓글 수

) {


    public static GetDetailPostRespDto from(Post post,  long commentCount) {


        return GetDetailPostRespDto.builder()
                .bordId(post.getBoard().getBoardId())
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .commentCount(commentCount)  // 댓글 수 포함
                .build();
    }
}