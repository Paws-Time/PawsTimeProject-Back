package com.pawstime.pawstime.domain.comment.dto.resp;

import com.pawstime.pawstime.domain.comment.entity.Comment;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record GetCommentRespDto(
    Long commentId,
    String content,
    Long postId,
    LocalDateTime createAt,
    LocalDateTime updateAt
) {

  public static GetCommentRespDto from(Comment comment) {
    return GetCommentRespDto.builder()
        .commentId(comment.getCommentId())
        .content(comment.getContent())
        .postId(comment.getPost().getPostId())
        .createAt(comment.getCreatedAt())
        .updateAt(comment.getUpdatedAt())
        .build();
  }
}
