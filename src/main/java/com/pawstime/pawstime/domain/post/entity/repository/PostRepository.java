package com.pawstime.pawstime.domain.post.entity.repository;

import com.pawstime.pawstime.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

/*    Long findByPostId(Post post);
    int countByPostIdLike(Long postId);*/


}




