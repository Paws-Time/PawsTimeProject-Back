package com.pawstime.pawstime.domain.post.dto.req;

import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.Post.PostCategory;
import com.pawstime.pawstime.domain.user.entity.User;


public record CreatePostReqDto(
    String title,           // 제목
    String content,         // 내용
    Long userId,            // 작성자 ID
    Long boardId,           // 게시판 ID
    PostCategory category,  // 카테고리
    int likesCount          //좋아요 수

) {

  public Post toEntity(User user, Board board) {
    return Post.builder()
        .title(this.title)
        .content(this.content)
        .user(user)
        .board(board)
        .category(this.category)
        .likesCount(0)
        .build();
  }

}
