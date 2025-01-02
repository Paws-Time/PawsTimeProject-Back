package com.pawstime.pawstime.domain.image.entity.repository;

import com.pawstime.pawstime.domain.image.dto.resp.GetImageRespDto;
import com.pawstime.pawstime.domain.image.entity.Image;
import com.pawstime.pawstime.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByPost_PostId(Long postId);
    void deleteByImageUrl(String imageUrl);  // 이미지 URL로 삭제

  @Query("SELECT i FROM Image i WHERE i.post.postId = :postId")
  Page<Image> getThumbnail(Long postId, Pageable pageable);

  @Query("SELECT i FROM Image i WHERE i.post.postId = :postId")
  List<Image> getImages(Long postId);

  @Query("SELECT i FROM Image i ORDER BY RAND()")
  Page<Image> getRandomImages(Pageable pageable);
}