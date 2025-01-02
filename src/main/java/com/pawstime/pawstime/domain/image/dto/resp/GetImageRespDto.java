package com.pawstime.pawstime.domain.image.dto.resp;

import com.pawstime.pawstime.domain.image.entity.Image;
import lombok.Builder;

@Builder
public record GetImageRespDto(
    Long imageId,
    String imageUrl,
    Long postId
) {

  public static GetImageRespDto from(Image image) {
    return GetImageRespDto.builder()
        .imageId(image.getImageId())
        .imageUrl(image.getImageUrl())
        .postId(image.getPost().getPostId())
        .build();
  }
}
