package com.pawstime.pawstime.domain.post.entity;

import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.image.entity.Image;
import com.pawstime.pawstime.domain.like.entity.Like;
import com.pawstime.pawstime.global.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board; // Board 엔티티와 관계 설정

    // 좋아요 관계 추가 (Post와 Like의 1:N 관계 설정)
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Like> likes = new ArrayList<>();  // 빈 리스트로 초기화

    @Column(name = "likes_count", nullable = false)
    private int likesCount = 0;

    @Column(name = "views", nullable = false)
    private int views = 0; // 조회수 기본값을 0으로 설정

    //게시글 생성/삭제 시 연관된 이미지도 함께 처리.
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>(); // 게시글과 연관된 이미지 리스트

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // 조회수를 증가시키는 메서드
    public void increaseViews() {
        this.views += 1;
    }

    // 좋아요 수 계산 (Like 엔티티와의 관계에서 개수를 계산)
    @Transient
    public int getLikesCount() {
        return likes.size();  // 연관된 Like 객체 수로 좋아요 수 계산
    }

    // 좋아요를 추가하는 메서드
    public void addLike(Like like) {
        this.likes.add(like);
    }

    // 좋아요를 삭제하는 메서드
    public void removeLike(Like like) {
        this.likes.remove(like);
    }

    public void incrementLikesCount() {
        this.likesCount++;
    }

    public void decrementLikesCount() {
        this.likesCount--;
    }


    // 연관된 이미지 추가
    public void addImage(Image image) {

        images.add(image);//이미지 리스트에 추가
        image.setPost(this);
    }


    //게시글에 여러 개의 이미지를 연결
    public void setImages(List<Image> images) {
        this.images = images;
        for (Image image : images) {
            image.setPost(this);
        }
    }

    // 게시글에 연결된 이미지가 없으면 기본이미지를 리스트로 반환
    public List<Image> getImagesWithDefault(String defaultImageUrl) {
        if (images == null || images.isEmpty()) {
            //기본 이미지 생성
            Image defaultImage = Image.builder()
                    .imageUrl(defaultImageUrl)
                    .isDefault(true)
                    .build();
            return List.of(defaultImage);
        }
        return images;
    }
}
