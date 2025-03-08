package com.pawstime.pawstime.domain.post.entity.repository;

import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

  Page<Post> findByUser(Pageable pageable, User user);
}




