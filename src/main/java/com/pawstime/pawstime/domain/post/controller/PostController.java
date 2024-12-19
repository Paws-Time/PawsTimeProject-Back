package com.pawstime.pawstime.domain.post.controller;

import com.pawstime.pawstime.domain.post.dto.req.CreatePostReqDto;
import com.pawstime.pawstime.domain.post.dto.req.UpdatePostReqDto;
import com.pawstime.pawstime.domain.post.dto.resp.GetDetailPostRespDto;
import com.pawstime.pawstime.domain.post.dto.resp.GetListPostRespDto;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import com.pawstime.pawstime.domain.post.facade.PostFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



@Slf4j
@Tag(name = "Post", description = "게시글 API")
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostFacade postFacade;
    private final PostRepository postRepository;

    @Operation(summary = "게시글 생성", description = "새로운 게시글을 생성할 수 있습니다.")
    @PostMapping("/posts")
    public ResponseEntity<String> createPost(@RequestBody @Valid CreatePostReqDto req,
                                             BindingResult bindingResult) {
        // 유효성 검사 오류가 있을 경우 처리
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getAllErrors().forEach(error ->
                    errorMessages.append(error.getDefaultMessage()).append("\n")
            );
            return ResponseEntity.badRequest().body("유효성 검사 실패: \n" + errorMessages.toString());
        }
        try {
            postFacade.createPost(req);
            return ResponseEntity.ok().body("게시글 생성이 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("게시글 생성 중 오류가 발생했습니다. " + e.getMessage());
        }
    }

    @Operation(summary = "게시글 수정", description = "게시글을 수정할 수 있습니다.")
    @PutMapping("/posts/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable Long postId,
                                             @RequestBody UpdatePostReqDto req, BindingResult bindingResult) {
        // 유효성 검사 오류 처리
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .findFirst()
                    .orElse("유효하지 않은 입력입니다.");
            return ResponseEntity.badRequest().body(errorMessage);
        }
        try {
            postFacade.updatePost(postId, req);
            return ResponseEntity.ok().body("게시글 수정이 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("게시글 수정 중 오류가 발생했습니다. " + e.getMessage());
        }
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제할 수 있습니다.")
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        try {
            postFacade.deletePost(postId);
            return ResponseEntity.ok("게시글 삭제가 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            // 이미 삭제된 게시글인 경우
            return ResponseEntity.badRequest().body("삭제할 수 없는 게시글입니다: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("게시글 삭제 중 오류가 발생했습니다. " + e.getMessage());
        }
    }

    @Operation(summary = "게시글 상세 조회", description = "게시글id로 상세조회를 할 수 있습니다.")
    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> getDetailPost(@PathVariable Long postId) {
        try {
            GetDetailPostRespDto postRespDto = postFacade.getDetailPost(postId);
            return ResponseEntity.ok(postRespDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("게시글 조회 중 오류가 발생했습니다. " + e.getMessage());
        }
    }

    @Operation(summary = "게시글 목록 조회", description = "게시글 목록조회를 할 수 있습니다.")
    @GetMapping("/posts")
    public ResponseEntity<?> getPosts(
            @RequestParam(required = false) Long boardId,  // 특정 게시판 필터링
            @RequestParam(required = false) String keyword,  // 제목/내용 검색
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,desc") String sort) {

        // sort 파라미터 분해
        String[] sortParams = sort.split(",");
        Sort.Direction direction = sortParams[1].equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        // sort 기준 설정 (기본값: createdAt)
        String sortField = sortParams[0];
        switch (sortField) {
            case "views":
                sortField = "views";  // 조회수 기준
                break;
            case "title":
                sortField = "title";  // 제목(가나다순) 기준
                break;
            case "createdAt":
            default:
                sortField = "createdAt";  // 기본적으로 작성일 기준
                break;
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));  // Pageable 생성

        // PostFacade를 통해 게시글 가져오기 (검색 및 게시판 필터링 포함)
        Page<GetListPostRespDto> posts = postFacade.getPosts(boardId, keyword, keyword, sortField, pageable);

        return ResponseEntity.ok().body(posts.getContent());
    }

}