package com.pawstime.pawstime.domain.info.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "hospitals")
public class HospitalInfo {

  @Id
  private Long id; // PK 매핑

  private String tel; // 전화번호

  private String add1; // 주소 1

  private String add2; // 주소 2

  private String name; // 병원 이름

  private String type; // 병원 유형

  @Column(name = "add_num")
  private Integer addNum; // 지역 번호

  private String x; // x 좌표

  private String y; // y 좌표
}
