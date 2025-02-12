package com.pawstime.pawstime.domain.info.dto.resp;

import com.pawstime.pawstime.domain.info.entity.HospitalInfo;
import lombok.Builder;

@Builder
public record GetHospitalInfoRespDto(
    Long id,
    String tel,
    String add1,
    String add2,
    String name,
    String type,
    Integer addNum,
    Double x,
    Double y
) {

  public static GetHospitalInfoRespDto from(HospitalInfo info) {
    return GetHospitalInfoRespDto.builder()
        .id(info.getId())
        .tel(info.getTel())
        .add1(info.getAdd1())
        .add2(info.getAdd2())
        .name(info.getName())
        .type(info.getType())
        .addNum(info.getAddNum())
        .x(Double.parseDouble(info.getX()))
        .y(Double.parseDouble(info.getY()))
        .build();
  }
}
