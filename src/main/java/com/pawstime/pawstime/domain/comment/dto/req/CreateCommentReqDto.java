package com.pawstime.pawstime.domain.comment.dto.req;

import com.pawstime.pawstime.domain.comment.entity.Comment;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record CreateCommentReqDto(
    @Schema(description = "댓글 내용", example = "댓글 내용을 남겨주세요.")
    String content
) {

  public Comment of(Post post, User user) {

    return Comment.builder()
        .post(post)
        .content(this.content)
        .user(user)
        .build();
  }
}
