package com.pawstime.pawstime.domain.board.entity;

import com.pawstime.pawstime.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

  public void updateTitle(String title) {
    this.title = title;
  }

  public void updateDescription(String description) {
    this.description = description;
  }
}