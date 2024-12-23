package com.pawstime.pawstime.domain.like.facade;

import com.pawstime.pawstime.domain.like.service.LikeService;
import com.pawstime.pawstime.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Transactional
@Component
@RequiredArgsConstructor
public class LikeFacade {
    private final LikeService likeService;

    public void toggleLike(Post post) {
        likeService.toggleLike(post);
    }
}
