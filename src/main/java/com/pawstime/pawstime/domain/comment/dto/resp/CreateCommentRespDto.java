package com.pawstime.pawstime.domain.comment.dto.resp;

import com.pawstime.pawstime.domain.comment.entity.Comment;
import lombok.Builder;

@Builder
public record CreateCommentRespDto(
        Long id,
        String content,
        String createdAt // 포맷된 생성일자
) {
    public static CreateCommentRespDto from(Comment comment) {
        return CreateCommentRespDto.builder()
                .id(comment.getCommentId())  // 댓글 ID
                .content(comment.getContent())  // 댓글 내용
                .createdAt(comment.getFormattedCreatedAt())  // 포맷된 생성일자
                .build();
    }
}