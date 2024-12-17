package com.pawstime.pawstime.domain.post.entity;

import com.pawstime.pawstime.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "board_id", nullable = false)
  private Long boardId;

  @Column(name = "likes_count", nullable = false)
  private int likesCount;

  @Column(name = "base_time")
  private LocalDateTime baseTime;

  @Enumerated(EnumType.STRING)
  @Column(name = "category", nullable = false)
  private PostCategory category;

  // 카테고리를 정의한 Enum
  public enum PostCategory {
    TECH, LIFESTYLE, EDUCATION, ENTERTAINMENT
  }
}
