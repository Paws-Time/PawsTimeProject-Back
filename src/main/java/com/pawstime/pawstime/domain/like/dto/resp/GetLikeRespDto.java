package com.pawstime.pawstime.domain.like.dto.resp;

import lombok.Builder;

@Builder
public record GetLikeRespDto(
        int likeCounts,
        boolean isLiked
) {
}
