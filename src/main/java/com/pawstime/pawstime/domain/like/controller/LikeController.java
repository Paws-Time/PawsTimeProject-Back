package com.pawstime.pawstime.domain.like.controller;

import com.pawstime.pawstime.domain.like.dto.req.CreateLikeReqDto;
import com.pawstime.pawstime.domain.like.facade.LikeFacade;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.facade.PostFacade;
import com.pawstime.pawstime.domain.user.entity.User;
import com.pawstime.pawstime.domain.user.facade.UserFacade;
import com.pawstime.pawstime.global.common.ApiResponse;
import com.pawstime.pawstime.global.enums.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name="Like", description = "좋아요 API")
@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeFacade likeFacade;

    @Operation(summary = "좋아요", description = "좋아요를 누르거나 취소할 수 있습니다.")
    @PutMapping("/{postId}")
    public ResponseEntity<ApiResponse<Integer>> toggleLike(@PathVariable Long postId){
        int likeCount = likeFacade.toggleLike(postId);
        return ApiResponse.generateResp(
                Status.SUCCESS, "좋아요 클릭",likeCount);
    }
}
