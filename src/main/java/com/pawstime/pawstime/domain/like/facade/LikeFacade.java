package com.pawstime.pawstime.domain.like.facade;

import com.pawstime.pawstime.domain.like.dto.req.CreateLikeReqDto;
import com.pawstime.pawstime.domain.like.entity.Like;
import com.pawstime.pawstime.domain.like.entity.repository.LikeRepository;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import com.pawstime.pawstime.domain.post.facade.PostFacade;
import com.pawstime.pawstime.domain.user.entity.User;
import com.pawstime.pawstime.domain.user.facade.UserFacade;
import com.pawstime.pawstime.global.exception.InvalidException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@Transactional
@Component
@RequiredArgsConstructor
public class LikeFacade {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserFacade userFacade;

    public int toggleLike(Long postId) {
        User user = userFacade.getCurrentUser();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new InvalidException("게시글을 찾을 수 없습니다."));

        log.info("User ID: {}", user.getUserId());
        log.info("Post ID: {}", post.getPostId());

        Like like = likeRepository.findByUserAndPost(post, user).orElse(null);

        if (like == null) {
            log.info("좋아요 추가");
            Like newLike = Like.builder()
                    .user(user)
                    .post(post)
                    .build();
            likeRepository.save(newLike);
            post.incrementLikesCount();
        } else {
            log.info("좋아요 삭제");
            likeRepository.delete(like);
            likeRepository.flush(); // 즉시 DB 반영
            post.getLikes().remove(like);
            post.decrementLikesCount();
        }

        log.info("최신 좋아요 개수: {}", post.getLikesCount());
        return post.getLikesCount();
    }
}