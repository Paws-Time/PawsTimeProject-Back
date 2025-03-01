package com.pawstime.pawstime.domain.post.dto.resp;

import com.pawstime.pawstime.domain.comment.entity.repository.CommentRepository;
import com.pawstime.pawstime.domain.post.entity.Post;
import java.time.LocalDateTime;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;

@Builder
public record GetDetailPostRespDto(
        Long boardId,
        Long postId,
        Long userId,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        int likeCounts,
        Long commentCount

) {

    public static GetDetailPostRespDto from(Post post,  long commentCount) {


        return GetDetailPostRespDto.builder()
                .boardId(post.getBoard().getBoardId())
                .postId(post.getPostId())
                .userId(post.getUser().getUserId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .commentCount(commentCount)
                .likeCounts(post.getLikesCount())
                .build();
    }
}