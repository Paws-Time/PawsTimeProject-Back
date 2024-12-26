package com.pawstime.pawstime.domain.post.entity;

import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.image.entity.Image;
import com.pawstime.pawstime.domain.like.entity.Like;
import com.pawstime.pawstime.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

  //@ManyToOne
  //@JoinColumn(name = "user_id", nullable = false)
  //private User user; // User 엔티티와 관계 설정

  @ManyToOne
  @JoinColumn(name = "board_id", nullable = false)
  private Board board; // Board 엔티티와 관계 설정

  // 좋아요 관계 추가 (Post와 Like의 1:N 관계 설정)
  @OneToMany(mappedBy = "post", fetch = FetchType.LAZY )
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
    images.add(image);
    image.setPost(this);
  }

  // 연관된 이미지 제거
  public void removeImage(Image image) {
    images.remove(image);
    image.setPost(null);
  }
}
