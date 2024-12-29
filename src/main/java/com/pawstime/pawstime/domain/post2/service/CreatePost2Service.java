package com.pawstime.pawstime.domain.post2.service;


import com.pawstime.pawstime.domain.post2.entity.Post2;
import com.pawstime.pawstime.domain.post2.entity.repository.Post2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePost2Service {
    private final Post2Repository post2Repository;

    public void createPost2(Post2 post2){

        //1.게시글 저장
        post2Repository.save(post2);

    }
}
