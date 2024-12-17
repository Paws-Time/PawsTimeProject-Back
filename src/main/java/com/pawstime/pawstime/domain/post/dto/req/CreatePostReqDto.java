package com.pawstime.pawstime.domain.post.dto.req;

import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.Post.PostCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record CreatePostReqDto(
    @Schema(description = "게시글 제목", example = "우리 집 구리 보러오세요")
    @NotBlank(message = "제목은 필수 입력값입니다.")
    @Size(min = 5, max = 20, message = "제목은 5자 이상, 20자 이하로 작성해야 합니다.")
    String title,           // 제목
    @Schema(description = "게시글 내용", example = "일상 내용")
    @NotBlank(message = "내용은 필수 입력값입니다.")
    @Size(min = 5, message = "내용은 최소 5자 이상이어야 합니다.")
    String content,         // 내용
    //Long userId,            // 작성자 ID
    Long boardId,           // 게시판 ID
    @NotNull(message = "카테고리는 필수 입력 값입니다.")
    PostCategory category,  // 카테고리
    int likesCount          //좋아요 수

) {

  public Post toEntity( Board board) {
    return Post.builder()
        .title(this.title)
        .content(this.content)
        //.user(user)
        .board(board)
        .category(this.category)
        .likesCount(0)
        .build();
  }
}
