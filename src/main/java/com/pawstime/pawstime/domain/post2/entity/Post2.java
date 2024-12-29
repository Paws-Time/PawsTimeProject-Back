package com.pawstime.pawstime.domain.post2.entity;

import com.pawstime.pawstime.domain.board.entity.Board;
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
@Table(name = "post2")
public class Post2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post2_id")
    private Long post2Id;

    @Column(nullable = false)
    private String title; // 게시글 제목

    @Column(nullable = false)
    private String content; // 게시글 내용

    @ManyToOne(fetch = FetchType.LAZY) // Board와 다대일 관계
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(nullable = false)
    private String name; // 사업장명

    @Column(nullable = false)
    private String address; // 도로명주소

    @Column(nullable = false)
    private Double latitude; // 좌표정보(x)

    @Column(nullable = false)
    private Double longitude; // 좌표정보(y)

    @Column(nullable = false)
    private String category; // 업종구분명

    @Column(nullable = true)
    private int regionCode; // 지역번호
}
