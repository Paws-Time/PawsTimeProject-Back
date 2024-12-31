package com.pawstime.pawstime.domain.info.controller;


import com.pawstime.pawstime.domain.info.dto.resp.GetHospitalInfoRespDto;
import com.pawstime.pawstime.domain.info.facade.InfoFacade;
import com.pawstime.pawstime.global.common.ApiResponse;
import com.pawstime.pawstime.global.enums.Status;
import com.pawstime.pawstime.global.exception.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Tag(name = "info", description = "정보게시판 API")
@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {

  private final InfoFacade infoFacade;

  @Operation(summary = "동물병원 정보 목록 조회")
  @GetMapping("/{addNum}")
  public ResponseEntity<ApiResponse<List<GetHospitalInfoRespDto>>> getHospitalInfo(
      @RequestParam(defaultValue = "0") int pageNo,
      @RequestParam(defaultValue = "10") int pageSize,
      @RequestParam(defaultValue = "name") String sortBy,
      @RequestParam(defaultValue = "DESC") String direction,
      @PathVariable int addNum
  ) {
    try {
      return ApiResponse.generateResp(
          Status.SUCCESS, null, infoFacade.readHospitalInfo(pageNo, pageSize, sortBy, direction, addNum).getContent());
    } catch (CustomException e) {
      Status status = Status.valueOf(e.getClass()
          .getSimpleName()
          .replace("Exception", "")
          .toUpperCase());
      return ApiResponse.generateResp(status, e.getMessage(), null);

    } catch (Exception e) {
      return ApiResponse.generateResp(
          Status.ERROR, "정보 게시판 조회 중 오류가 발생하였습니다 : " + e.getMessage(), null);
    }
  }
}
