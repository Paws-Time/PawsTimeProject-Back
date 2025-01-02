package com.pawstime.pawstime.domain.image.service;

import com.pawstime.pawstime.domain.image.dto.resp.GetImageRespDto;
import com.pawstime.pawstime.domain.image.entity.Image;
import com.pawstime.pawstime.domain.image.entity.repository.ImageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadImageService {

  private final ImageRepository imageRepository;

  public Page<Image> getThumbnail(Long postId, Pageable pageable) {
    return imageRepository.getThumbnail(postId, pageable);
  }
}
