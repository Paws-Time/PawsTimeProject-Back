package com.pawstime.pawstime.domain.comment.controller;

import com.pawstime.pawstime.domain.comment.dto.req.CreateCommentReqDto;
import com.pawstime.pawstime.domain.comment.facade.CommentFacade;
import com.pawstime.pawstime.global.common.ApiResponse;
import com.pawstime.pawstime.global.enums.Status;
import com.pawstime.pawstime.global.exception.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Comment", description = "댓글 API")
@RestController
@RequestMapping("/{postId}")
@RequiredArgsConstructor
public class CommentController {

  private final CommentFacade commentFacade;

  @Operation(summary = "댓글 생성", description = "댓글 생성 기능")
  @PostMapping("/comments")
  public ApiResponse<Void> createComment(
      @PathVariable Long postId, @RequestBody CreateCommentReqDto req) {
    try {
      commentFacade.createComment(postId, req);

      return ApiResponse.generateResp(Status.CREATE, "댓글 생성이 완료되었습니다.", null);
    } catch (CustomException e) {
      Status status = Status.valueOf(e.getClass().getSimpleName().replace("Exception", "").toUpperCase());
      return ApiResponse.generateResp(status, e.getMessage(), null);
    } catch (Exception e) {
      return ApiResponse.generateResp(Status.ERROR, "댓글 생성 중 오류가 발생하였습니다 : " + e.getMessage(), null);
    }
  }
}
