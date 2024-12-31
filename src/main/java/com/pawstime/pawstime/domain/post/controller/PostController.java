package com.pawstime.pawstime.domain.post.controller;

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
import com.pawstime.pawstime.global.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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


    @Operation(summary = "게시글 생성", description = "게시글을 생성 할 수 있습니다.")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<Void>> createPost(@RequestPart("data") CreatePostReqDto req, @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        try {
            // 이미지를 S3에 업로드하고, 업로드된 이미지 URL들을 리스트로 반환
            List<String> imageUrls = images == null || images.isEmpty()
                    ? Collections.emptyList()
                    : s3Service.uploadImages(images);

            // 업로드된 이미지 URL들을 포함해 게시글 생성
            postFacade.createPost(req, imageUrls); // 이미지 URL을 포함하여 게시글 생성

            return ApiResponse.generateResp(Status.CREATE, "게시글 생성이 완료되었습니다.", null);
        } catch (CustomException e) {
            Status status = Status.valueOf(e.getClass().getSimpleName().replace("Exception", "").toUpperCase());
            log.info("** {} **", status);
            return ApiResponse.generateResp(status, e.getMessage(), null);
        } catch (Exception e) {
            return ApiResponse.generateResp(Status.ERROR, "게시글 생성 중 오류가 발생하였습니다 : " + e.getMessage(), null);
        }
    }


    @Operation(summary = "게시글 수정", description = "게시글을 수정할 수 있습니다.")
    @PutMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> updatePost(@Valid @PathVariable Long postId,
                                                        @RequestBody UpdatePostReqDto req) {
        try {
            // Facade에서 예외를 던지도록 처리
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
    @PostMapping("/{postId}/like")
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