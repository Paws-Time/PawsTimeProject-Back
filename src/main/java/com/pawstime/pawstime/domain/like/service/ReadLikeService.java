package com.pawstime.pawstime.domain.like.service;

import com.pawstime.pawstime.domain.like.entity.Like;
import com.pawstime.pawstime.domain.like.entity.repository.LikeRepository;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReadLikeService {
    private final LikeRepository likeRepository;

    public Optional<Like> findByUserAndPost(Post post, User user){return likeRepository.findByUserAndPost(post, user);}



}
