package com.pawstime.pawstime.domain.image.entity;

import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @Column(nullable = false)
    private String imageUrl;

    @Column(name = "is_default", nullable = false)
    private boolean isDefault = false; // 기본 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = true)// 기본 이미지는 특정 Post와 관계없을 수 있음
    private Post post;

    // 이미지를 Post에 추가하는 메서드
    public void setPost(Post post) {
        this.post = post;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }
}