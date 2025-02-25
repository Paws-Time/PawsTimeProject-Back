package com.pawstime.pawstime.domain.like.entity.repository;


import com.pawstime.pawstime.domain.like.entity.Like;
import com.pawstime.pawstime.domain.post.entity.Post;
import java.util.Optional;

import com.pawstime.pawstime.domain.user.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    //유저와 게시글에 대한 좋아요를 눌렀는지 확인
    @Query("SELECT l FROM Like l WHERE l.user = :user AND l.post = :post")
    Optional<Like> findByUserAndPost( @Param("post") Post post, @Param("user") User user);

    //게시글의 좋아요 갯수 조회
    @Query("SELECT COUNT(l) FROM Like l WHERE l.post = :post")
    int countByPost(@Param("post")Post post);
}
