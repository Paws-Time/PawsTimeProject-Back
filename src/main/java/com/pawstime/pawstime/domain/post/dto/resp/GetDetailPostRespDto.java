package com.pawstime.pawstime.domain.post.dto.resp;

import com.pawstime.pawstime.domain.post.entity.Post;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record GetDetailPostRespDto(
        Long postId,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
        //String nick  작성자 닉네임
) {
    public static GetDetailPostRespDto from(Post post) {
       // String nick = post.getUser() != null ? post.getUser().getNick() : "알 수 없음"; // 예시: 작성자 닉네임 처리
        return GetDetailPostRespDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
               // .nick(nick)
                .build();
    }
}