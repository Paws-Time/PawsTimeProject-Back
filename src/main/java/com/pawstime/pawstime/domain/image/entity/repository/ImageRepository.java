package com.pawstime.pawstime.domain.image.entity.repository;

import com.pawstime.pawstime.domain.image.entity.Image;
import com.pawstime.pawstime.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByPost_PostId(Long postId);
    void deleteByImageUrl(String imageUrl);  // 이미지 URL로 삭제
}
