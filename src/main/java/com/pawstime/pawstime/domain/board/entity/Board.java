package com.pawstime.pawstime.domain.board.entity;

import com.pawstime.pawstime.domain.board.enums.BoardType;
import com.pawstime.pawstime.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "board")
public class Board extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "board_id")
  private Long boardId;

  @Column(nullable = false)
  private String title;

  private String description;

  @Enumerated(EnumType.STRING)
  private BoardType boardType;// 게시판 타입 (일반 게시판, 정보 게시판 등)

  private boolean allowComments; // 댓글 허용 여부

  private boolean allowReports;  // 신고 허용 여부

  // 게시판 타입에 맞게 댓글 및 신고 허용 여부 설정
  public void setBoardType(BoardType boardType) {
    this.boardType = boardType;
    this.allowComments = boardType.isAllowComments(); // BoardType에 따른 댓글 허용 여부 설정
    this.allowReports = boardType.isAllowReports();   // BoardType에 따른 신고 허용 여부 설정
  }


  public void updateTitle(String title) {
    this.title = title;
  }

  public void updateDescription(String description) {
    this.description = description;
  }

}