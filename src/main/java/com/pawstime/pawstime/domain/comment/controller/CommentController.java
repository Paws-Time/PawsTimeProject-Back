package com.pawstime.pawstime.domain.comment.controller;

import com.pawstime.pawstime.domain.comment.dto.req.CreateCommentReqDto;
import com.pawstime.pawstime.domain.comment.dto.req.UpdateCommentReqDto;
import com.pawstime.pawstime.domain.comment.dto.resp.GetCommentRespDto;
import com.pawstime.pawstime.domain.comment.facade.CommentFacade;
import com.pawstime.pawstime.global.common.ApiResponse;
import com.pawstime.pawstime.global.enums.Status;
import com.pawstime.pawstime.global.exception.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Comment", description = "댓글 API")
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

  private final CommentFacade commentFacade;

  @Operation(summary = "댓글 생성", description = "특정 게시글에 댓글을 생성하는 기능입니다.")
  @PostMapping("/{postId}")
  public ResponseEntity<ApiResponse<Void>> createComment(
      @PathVariable Long postId, @RequestBody CreateCommentReqDto req) {
    try {
      commentFacade.createComment(postId, req);

      return ApiResponse.generateResp(Status.CREATE, "댓글 생성이 완료되었습니다.", null);

    } catch (CustomException e) {
      Status status = Status.valueOf(e.getClass()
          .getSimpleName()
          .replace("Exception", "")
          .toUpperCase());
      return ApiResponse.generateResp(status, e.getMessage(), null);

    } catch (Exception e) {
      return ApiResponse.generateResp(
          Status.ERROR, "댓글 생성 중 오류가 발생하였습니다 : " + e.getMessage(), null);
    }
  }

  @Operation(summary = "댓글 전체 목록 조회", description = "모든 게시글에 달린 댓글을 조회합니다.")
  @GetMapping("/listAll")
  public ResponseEntity<ApiResponse<List<GetCommentRespDto>>> getCommentAll(
      @RequestParam(defaultValue = "0") int pageNo,
      @RequestParam(defaultValue = "10") int pageSize,
      @RequestParam(defaultValue = "createdAt") String sortBy,
      @RequestParam(defaultValue = "DESC") String direction
  ) {
    try {
      return ApiResponse.generateResp(Status.SUCCESS, null,
          commentFacade.getCommentAll(pageNo, pageSize, sortBy, direction).getContent());

    } catch (CustomException e) {
      Status status = Status.valueOf(e.getClass()
          .getSimpleName()
          .replace("Exception", "")
          .toUpperCase());
      return ApiResponse.generateResp(status, e.getMessage(), null);

    } catch (Exception e) {
      return ApiResponse.generateResp(
          Status.ERROR, "전체 댓글 조회 중 오류가 발생하였습니다 : " + e.getMessage(), null);
    }
  }

  @Operation(summary = "특정 게시글 댓글 조회", description = "선택한 게시글에 달린 댓글 목록을 조회합니다.")
  @GetMapping("/list/{postId}")
  public ResponseEntity<ApiResponse<List<GetCommentRespDto>>> getCommentByPost(
      @PathVariable Long postId,
      @RequestParam(defaultValue = "0") int pageNo,
      @RequestParam(defaultValue = "10") int pageSize,
      @RequestParam(defaultValue = "createdAt") String sortBy,
      @RequestParam(defaultValue = "DESC") String direction
  ) {
    try {
      return ApiResponse.generateResp(Status.SUCCESS, null,
          commentFacade.getCommentByPost(postId, pageNo, pageSize, sortBy, direction).getContent());

    } catch (CustomException e) {
      Status status = Status.valueOf(e.getClass()
          .getSimpleName()
          .replace("Exception", "")
          .toUpperCase());
      return ApiResponse.generateResp(status, e.getMessage(), null);

    } catch (Exception e) {
      return ApiResponse.generateResp(
          Status.ERROR, "게시글 댓글 조회 중 오류가 발생하였습니다 : " + e.getMessage(), null);
    }
  }

  @Operation(summary = "댓글 삭제", description = "선택한 댓글을 삭제합니다.")
  @DeleteMapping("/{commentId}")
  public ResponseEntity<ApiResponse<Void>> deleteComment(@PathVariable Long commentId) {
    try {

      commentFacade.deleteComment(commentId);

      return ApiResponse.generateResp(Status.DELETE, "댓글이 삭제되었습니다.", null);

    } catch (CustomException e) {
      Status status = Status.valueOf(e.getClass()
          .getSimpleName()
          .replace("Exception", "")
          .toUpperCase());
      return ApiResponse.generateResp(status, e.getMessage(), null);

    } catch (Exception e) {
      return ApiResponse.generateResp(
          Status.ERROR, "댓글 삭제 중 오류가 발생하였습니다 : " + e.getMessage(), null);
    }
  }
  @Operation(summary = "댓글 수정", description = "선택한 댓글을 수정합니다.")
  @PutMapping("/{commentId}")
  public ResponseEntity<ApiResponse<Void>> updateComment( @PathVariable Long commentId, @RequestBody UpdateCommentReqDto req){
   try{
     commentFacade.updateComment(commentId, req);
     return ApiResponse.generateResp(
             Status.UPDATE, "댓글 수정이 완료되었습니다.", null);
   }
   catch (CustomException e){
     Status status = Status.valueOf(e.getClass()
             .getSimpleName()
             .replace("Exception", "")
             .toUpperCase());
     return ApiResponse.generateResp(status, e.getMessage(), null);
   } catch (Exception e) {
     return ApiResponse.generateResp(
             Status.ERROR, "댓글 수정 중 오류가 발생했습니다. : " + e.getMessage(), null);

   }
  }
}
