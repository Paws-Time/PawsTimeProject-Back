package com.pawstime.pawstime.domain.profileImg.dto.resp;

import com.pawstime.pawstime.domain.profileImg.entity.ProfileImg;
import lombok.Builder;

@Builder
public record GetProfileImgRespDto(
        String profileImgUrl
) {
    public static GetProfileImgRespDto from(ProfileImg profileImg) {
        return GetProfileImgRespDto.builder()
                .profileImgUrl(profileImg.getProfileImgUrl())
                .build();
    }
}
