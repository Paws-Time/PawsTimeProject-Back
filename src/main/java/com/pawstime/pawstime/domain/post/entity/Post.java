package com.pawstime.pawstime.domain.post.entity;

import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.global.entity.BaseEntity;
import jakarta.persistence.*;
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

  //@ManyToOne
  //@JoinColumn(name = "user_id", nullable = false)
  //private User user; // User 엔티티와 관계 설정

  @ManyToOne
  @JoinColumn(name = "board_id", nullable = false)
  private Board board; // Board 엔티티와 관계 설정

  @Column(name = "likes_count", nullable = false)
  private int likesCount = 0; // 기본값을 0으로 설정

  @Column(name = "views", nullable = false)
  private int views = 0; // 조회수 기본값을 0으로 설정

  @Enumerated(EnumType.STRING)
  @Column(name = "category", nullable = false)
  private PostCategory category;

  // 카테고리를 정의한 Enum
  public enum PostCategory {
    TECH, LIFESTYLE, EDUCATION, ENTERTAINMENT
  }

  public void setTitle(String title){
    this.title = title;
  }
  public void setContent(String content){
    this.content = content;
  }
  public void setCategory(PostCategory category){
    this.category = category;
  }

  // 조회수를 증가시키는 메서드
  public void increaseViews() {
    this.views += 1;
  }



}
