package com.pawstime.pawstime.domain.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawstime.pawstime.domain.image.dto.resp.GetImageRespDto;
import com.pawstime.pawstime.domain.like.facade.LikeFacade;
import com.pawstime.pawstime.domain.post.dto.req.CreatePostReqDto;
import com.pawstime.pawstime.domain.post.dto.req.UpdatePostReqDto;
import com.pawstime.pawstime.domain.post.dto.resp.GetDetailPostRespDto;
import com.pawstime.pawstime.domain.post.dto.resp.GetListPostRespDto;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.facade.PostFacade;
import com.pawstime.pawstime.domain.post.service.S3Service;
import com.pawstime.pawstime.global.common.ApiResponse;
import com.pawstime.pawstime.global.enums.Status;
import com.pawstime.pawstime.global.exception.CustomException;
import com.pawstime.pawstime.global.exception.InvalidException;
import com.pawstime.pawstime.global.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Tag(name = "Post", description = "게시글 API")
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostFacade postFacade;
    private final LikeFacade likeFacade;
    private final S3Service s3Service;

    @Operation(summary = "게시글 생성", description = "게시글을 생성할 수 있습니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<Long>> createPost(
            @RequestBody CreatePostReqDto req) {  // JSON 데이터를 DTO로 바로 받기

        try {
            // 게시글 생성 (이미지 URL은 아직 없음)
            Long postId = postFacade.createPost(req, new ArrayList<>());  // 이미지가 없으면 빈 리스트 전달

            // 성공 응답 반환
            return ApiResponse.generateResp(Status.CREATE, "게시글 생성이 완료되었습니다. ", postId);
        } catch (CustomException e) {
            // 커스텀 예외 처리
            Status status = Status.valueOf(e.getClass().getSimpleName().replace("Exception", "").toUpperCase());
            log.error("Custom exception occurred: {}", status);
            return ApiResponse.generateResp(status, e.getMessage(), null);
        } catch (Exception e) {
            // 일반 예외 처리
            log.error("Unexpected error: {}", e.getMessage());
            return ApiResponse.generateResp(Status.ERROR, "게시글 생성 중 오류가 발생하였습니다: " + e.getMessage(), null);
        }
    }

    @Operation(summary = "게시글 이미지 업로드", description = "이미지 업로드 후 게시글과 연결합니다.")
    @PostMapping(value = "/{postId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Void>> uploadImages(
            @PathVariable Long postId,
            @RequestPart("images") List<MultipartFile> images) {
        // 파일 유효성 검사
        if (images == null || images.isEmpty()) {
            return ApiResponse.generateResp(Status.ERROR, "이미지 파일을 업로드해주세요.", null);
        }

        try {
            // 게시글 상태 체크
            Post post = postFacade.getPostId(postId);
            if (post.isDelete()) {
                throw new InvalidException("삭제된 게시글에는 이미지를 추가할 수 없습니다.");
            }

            // S3에 업로드 후 이미지 URL 리스트 받기
            List<String> imageUrls = s3Service.uploadFile(images);

            // 게시글에 이미지 추가 (DB에 이미지 정보 저장)
            postFacade.addImagesToPost(postId, imageUrls);

            return ApiResponse.generateResp(Status.CREATE, "게시글과 이미지가 성공적으로 업로드되었습니다.", null);
        } catch (Exception e) {
            log.error("이미지 업로드 중 오류 발생: {}", e.getMessage(), e);
            return ApiResponse.generateResp(Status.ERROR, "이미지 업로드 실패: " + e.getMessage(), null);
        }
    }

    @Operation(summary = "게시글 수정", description = "게시글을 수정할 수 있습니다.")
    @PutMapping( "/{postId}")
    public ResponseEntity<ApiResponse<Void>> updatePost(
            @PathVariable Long postId,
            @RequestBody UpdatePostReqDto req
    ) {
        try {
            postFacade.updatePost(postId, req);
            return ApiResponse.generateResp(Status.UPDATE, "게시글 수정이 완료되었습니다.", null);
        } catch (CustomException e) {
            Status status = Status.valueOf(e.getClass().getSimpleName().replace("Exception", "").toUpperCase());
            log.info("** {} **", status);
            return ApiResponse.generateResp(status, e.getMessage(), null);
        } catch (Exception e) {
            return ApiResponse.generateResp(Status.ERROR, "게시글 수정 중 오류가 발생하였습니다: " + e.getMessage(), null);
        }
    }

    @Operation(summary = "게시글 이미지 수정", description = "기존 이미지를 삭제하거나 새로운 이미지를 추가합니다.")
    @PutMapping(value = "/{postId}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Void>> updatePostImages(
            @PathVariable Long postId,
            @RequestParam(value = "deletedImageIds", required = false) List<Long> deletedImageIds,
            @RequestPart(value = "newImages", required = false) List<MultipartFile> newImages) {

        try {
            // 이미지 수정 요청 처리
            postFacade.updatePostImages(postId, deletedImageIds, newImages);

            return ApiResponse.generateResp(Status.UPDATE, "게시글 이미지가 수정되었습니다.", null);
        } catch (Exception e) {
            log.error("게시글 이미지 수정 중 오류 발생: {}", e.getMessage(), e);
            return ApiResponse.generateResp(Status.ERROR, "이미지 수정 실패: " + e.getMessage(), null);
        }
    }



    @Operation(summary = "게시글 삭제", description = "게시글을 삭제할 수 있습니다.")
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Long postId) {
        try {
            postFacade.deletePost(postId);
            return ApiResponse.generateResp(Status.DELETE, "게시글 삭제가 완료되었습니다.", null);
        } catch (NotFoundException e) {
            // NotFoundException이 발생하면 해당 게시글이 존재하지 않거나 이미 삭제된 경우 처리
            return ApiResponse.generateResp(Status.NOTFOUND, e.getMessage(), null);
        } catch (Exception e) {
            // 기타 예외 처리
            return ApiResponse.generateResp(Status.ERROR, "게시글 삭제 중 오류가 발생하였습니다: " + e.getMessage(), null);
        }
    }

    @Operation(summary = "게시글 상세 조회", description = "게시글 ID로 상세 조회를 할 수 있습니다.")
    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<GetDetailPostRespDto>> getDetailPost(@PathVariable Long postId) {
        try {
            GetDetailPostRespDto postRespDto = postFacade.getDetailPost(postId);
            return ApiResponse.generateResp(Status.SUCCESS, "게시글 상세 조회 성공", postRespDto);
        } catch (CustomException e) {
            Status status = Status.valueOf(e.getClass().getSimpleName().replace("Exception", "").toUpperCase());
            return ApiResponse.generateResp(status, e.getMessage(), null);
        } catch (Exception e) {
            return ApiResponse.generateResp(Status.ERROR, "게시글 조회 중 오류가 발생했습니다: " + e.getMessage(), null);
        }
    }

    @Operation(summary = "게시글 목록 조회", description = "게시글 목록 조회를 할 수 있습니다.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<GetListPostRespDto>>> getPosts(
            @RequestParam(required = false) Long boardId,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,desc") String sort) {

        try {
            // Pageable 객체 생성
            Pageable pageable = createPageable(sort, page, size);

            // Facade 호출: 게시글 목록 조회
            Page<GetListPostRespDto> posts = postFacade.getPostList(boardId, keyword, pageable, sort.split(",")[0], sort.split(",")[1]);

            // 성공 응답
            return ApiResponse.generateResp(Status.SUCCESS, "게시글 목록 조회 성공", posts.getContent());
        } catch (NotFoundException e) {
            // 존재하지 않는 게시판 ID 예외 처리
            return ApiResponse.generateResp(Status.NOTFOUND, e.getMessage(), null);
        } catch (Exception e) {
            // 기타 예외 처리
            return ApiResponse.generateResp(Status.ERROR, "게시글 목록 조회 중 오류가 발생했습니다. " + e.getMessage(), null);
        }
    }

    @Operation(summary = "게시글별 대표 이미지 조회")
    @GetMapping("/{postId}/thumbnail")
    public ResponseEntity<ApiResponse<List<GetImageRespDto>>> getThumbnail(@PathVariable Long postId) {
        try {
            return ApiResponse.generateResp(Status.SUCCESS, null, postFacade.getThumbnail(postId).getContent());
        } catch (CustomException e) {
            Status status = Status.valueOf(e.getClass()
                .getSimpleName()
                .replace("Exception", "")
                .toUpperCase());
            return ApiResponse.generateResp(status, e.getMessage(), null);

        } catch (Exception e) {
            return ApiResponse.generateResp(
                Status.ERROR, "썸네일 이미지 조회 중 오류가 발생하였습니다 : " + e.getMessage(), null);
        }
    }

    @Operation(summary = "게시글별 이미지 전체 조회")
    @GetMapping("/{postId}/images")
    public ResponseEntity<ApiResponse<List<GetImageRespDto>>> getImages(@PathVariable Long postId) {
        try {
            return ApiResponse.generateResp(Status.SUCCESS, null, postFacade.getImages(postId));
        } catch (CustomException e) {
            Status status = Status.valueOf(e.getClass()
                .getSimpleName()
                .replace("Exception", "")
                .toUpperCase());
            return ApiResponse.generateResp(status, e.getMessage(), null);

        } catch (Exception e) {
            return ApiResponse.generateResp(
                Status.ERROR, "게시글별 이미지 전체 조회 중 오류가 발생하였습니다 : " + e.getMessage(), null);
        }
    }

    // Pageable 객체 생성 (정렬 처리)
    public Pageable createPageable(String sort, int page, int size) {
        String[] sortParams = sort.split(",");

        // 기본값 처리: sort 파라미터가 잘못된 경우 'createdAt'으로 기본 설정
        String sortField = sortParams.length > 0 ? sortParams[0] : "createdAt";
        String direction = sortParams.length > 1 ? sortParams[1] : "desc";

        // Direction 설정
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        // Pageable 객체 생성
        return PageRequest.of(page, size, Sort.by(sortDirection, sortField));
    }

    @PostMapping("/{postId}/likes")
    @Operation(summary = "좋아요", description = "게시글에 좋아요를 누를 수 있습니다.")

    public ResponseEntity<ApiResponse<Integer>> toggleLike(@PathVariable Long postId) {
        try {
            Post post = postFacade.getPostId(postId);  // 게시글 가져오기
            likeFacade.toggleLike(post);  // 좋아요 토글 처리
            return ApiResponse.generateResp(Status.SUCCESS, null, post.getLikesCount());  // 좋아요 수 반환
        } catch (NotFoundException e) {
            return ApiResponse.generateResp(Status.NOTFOUND, e.getMessage(), null);
        }
    }
}