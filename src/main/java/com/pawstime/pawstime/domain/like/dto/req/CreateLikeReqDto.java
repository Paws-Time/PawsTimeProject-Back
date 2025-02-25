package com.pawstime.pawstime.domain.like.dto.req;

import com.pawstime.pawstime.domain.like.entity.Like;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateLikeReqDto(
        @Schema(description = "게시글 아이디", example = "1")
        @NotBlank(message = "게시글 아이디는 필수입니다.")
        Long postId
) {
    public Like toEntity(User user, Post post){
        return Like.builder()
                .user(user) //현재 로그인한 사용자
                .post(post) //받아온 postId로 조회한 게시글
                .build();
    }

}
