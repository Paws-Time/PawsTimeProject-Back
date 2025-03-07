package com.pawstime.pawstime.domain.comment.controller;

import com.pawstime.pawstime.domain.comment.dto.req.CreateCommentReqDto;
import com.pawstime.pawstime.domain.comment.dto.req.UpdateCommentReqDto;
import com.pawstime.pawstime.domain.comment.dto.resp.CreateCommentRespDto;
import com.pawstime.pawstime.domain.comment.dto.resp.GetCommentRespDto;
import com.pawstime.pawstime.domain.comment.facade.CommentFacade;
import com.pawstime.pawstime.domain.post.dto.resp.GetListPostRespDto;
import com.pawstime.pawstime.global.common.ApiResponse;
import com.pawstime.pawstime.global.enums.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Tag(name = "Comment", description = "댓글 API")
@RestController
@RequiredArgsConstructor
public class CommentController {

  private final CommentFacade commentFacade;


  @Operation(summary = "댓글 생성", description = "특정 게시글에 댓글을 생성하는 기능입니다.")
  @PostMapping("/posts/{postId}/comments")
  public ResponseEntity<ApiResponse<CreateCommentRespDto>> createComment(
          @PathVariable Long postId,
          @RequestBody CreateCommentReqDto req,
          HttpServletRequest httpServletRequest) {
      // 댓글 생성 후 포맷된 응답 DTO 반환
      CreateCommentRespDto responseDto = commentFacade.createComment(postId, req, httpServletRequest);

      return ApiResponse.generateResp(Status.CREATE, "댓글 생성이 완료되었습니다.", responseDto);
  }


  @Operation(summary = "댓글 전체 목록 조회", description = "모든 게시글에 달린 댓글을 조회합니다.")
  @GetMapping("/comments")
  public ResponseEntity<ApiResponse<List<GetCommentRespDto>>> getCommentAll(
      @RequestParam(defaultValue = "0") int pageNo,
      @RequestParam(defaultValue = "10") int pageSize,
      @RequestParam(defaultValue = "createdAt") String sortBy,
      @RequestParam(defaultValue = "DESC") String direction
  ) {
      return ApiResponse.generateResp(Status.SUCCESS, null,
          commentFacade.getCommentAll(pageNo, pageSize, sortBy, direction).getContent());
  }

  @Operation(summary = "특정 게시글 댓글 조회", description = "선택한 게시글에 달린 댓글 목록을 조회합니다.")
  @GetMapping("/posts/{postId}/comments")
  public ResponseEntity<ApiResponse<List<GetCommentRespDto>>> getCommentByPost(
      @PathVariable Long postId,
      @RequestParam(defaultValue = "0") int pageNo,
      @RequestParam(defaultValue = "10") int pageSize,
      @RequestParam(defaultValue = "createdAt") String sortBy,
      @RequestParam(defaultValue = "DESC") String direction
  ) {
      return ApiResponse.generateResp(Status.SUCCESS, null,
          commentFacade.getCommentByPost(postId, pageNo, pageSize, sortBy, direction).getContent());
  }

  @Operation(summary = "현재 로그인한 사용자가 작성한 댓글 목록 조회")
  @GetMapping("/me")
  public ResponseEntity<ApiResponse<List<GetCommentRespDto>>> getCommentListByUser(
      @RequestParam(defaultValue = "0") int pageNo,
      @RequestParam(defaultValue = "10") int pageSize,
      @RequestParam(defaultValue = "createdAt") String sortBy,
      @RequestParam(defaultValue = "DESC") String direction,
      HttpServletRequest httpServletRequest
  ) {
    return ApiResponse.generateResp(
        Status.SUCCESS, null, commentFacade.getCommentListByUser(pageNo, pageSize, sortBy, direction, httpServletRequest).getContent()
    );
  }

  @Operation(summary = "댓글 삭제", description = "선택한 댓글을 삭제합니다.")
  @DeleteMapping("/posts/{postId}/comments/{commentId}")
  public ResponseEntity<ApiResponse<Void>> deleteComment(
      @PathVariable Long postId, @PathVariable Long commentId, HttpServletRequest httpServletRequest
  ) {
      commentFacade.deleteComment(postId, commentId, httpServletRequest);

      return ApiResponse.generateResp(Status.DELETE, "댓글이 삭제되었습니다.", null);
  }

  @Operation(summary = "댓글 수정", description = "선택한 댓글을 수정합니다.")
  @PutMapping("/posts/{postId}/comments/{commentId}")
  public ResponseEntity<ApiResponse<Void>> updateComment(
      @PathVariable Long postId, @PathVariable Long commentId, @RequestBody UpdateCommentReqDto req,
      HttpServletRequest httpServletRequest
  ){
     commentFacade.updateComment(postId, commentId, req, httpServletRequest);
     return ApiResponse.generateResp(
             Status.UPDATE, "댓글 수정이 완료되었습니다.", null);
  }
}
