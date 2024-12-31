package com.pawstime.pawstime.domain.like.service;

import com.pawstime.pawstime.domain.like.entity.Like;
import com.pawstime.pawstime.domain.like.entity.repository.LikeRepository;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import com.pawstime.pawstime.global.exception.NotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    public void toggleLike(Post post) {
        if (post.isDelete()) {
            throw new NotFoundException("삭제된 게시글에는 좋아요를 누를 수 없습니다.");
        }

        Optional<Like> existingLike = likeRepository.findByPost(post);

        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            post.removeLike(existingLike.get());
            post.decrementLikesCount();
        } else {
            Like newLike = Like.builder()
                    .post(post)
                    .build();
            likeRepository.save(newLike);
            post.addLike(newLike);
            post.incrementLikesCount();
        }

        postRepository.save(post);  // 변경된 post 저장
    }
}
