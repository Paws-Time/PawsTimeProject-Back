package com.pawstime.pawstime.domain.post2.controller;


import com.pawstime.pawstime.domain.post2.dto.req.CreatePost2ReqDto;
import com.pawstime.pawstime.domain.post2.facade.Post2Facade;
import com.pawstime.pawstime.global.common.ApiResponse;
import com.pawstime.pawstime.global.enums.Status;
import com.pawstime.pawstime.global.exception.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Tag(name = "Post1", description = "정보게시판 API")
@RestController
@RequestMapping("/post2")
@RequiredArgsConstructor
public class Post2Controller {

    private final Post2Facade post2Facade;

    @Operation(summary = "게시글 생성", description = "게시글을 생성 할 수 있습니다.")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<Void>> createPost2(@RequestBody CreatePost2ReqDto req) {
        try {
            post2Facade.createPost2(req);
            return ApiResponse.generateResp(Status.CREATE, "게시글 생성이 완료되었습니다.", null);
        } catch (CustomException e) {
            Status status = Status.valueOf(e.getClass().getSimpleName().replace("Exception", "").toUpperCase());
            log.info("** {} **", status);
            return ApiResponse.generateResp(status, e.getMessage(), null);
        } catch (Exception e) {
            return ApiResponse.generateResp(Status.ERROR, "게시글 생성 중 오류가 발생하였습니다 : " + e.getMessage(), null);
        }
    }
}
