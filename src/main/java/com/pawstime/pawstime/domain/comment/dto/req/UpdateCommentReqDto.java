package com.pawstime.pawstime.domain.comment.dto.req;

import com.pawstime.pawstime.domain.comment.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateCommentReqDto(
        @Schema(description = "수정할 댓글 내용", example = "수정된 댓글 내용입니다.")
        String content
) {
    public Comment update (Comment comment){
        return Comment.builder()
                .commentId(comment.getCommentId())
                .post(comment.getPost())
                .content(this.content)
                .build();
    }
}
