package com.pawstime.pawstime.domain.post.service;

import com.pawstime.pawstime.domain.board.entity.repository.BoardRepository;
import com.pawstime.pawstime.domain.post.dto.resp.GetListPostRespDto;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import com.pawstime.pawstime.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GetListPostService {

    private final PostRepository postRepository;

    // 게시글 목록 조회
    public Page<GetListPostRespDto> getPostList(Long boardId, String keyword, Pageable pageable, String sortBy, String direction) {
        // 기본적으로 삭제되지 않은 게시글만 조회하는 Specification
        Specification<Post> spec = Specification.where(PostSpecification.isNotDeleted())
                .and(PostSpecification.hasKeyword(keyword))
                .and(PostSpecification.belongsToBoard(boardId))
                .and(PostSpecification.boardIsNotDeleted())
                .and(PostSpecification.orderBy(sortBy, direction)); // 정렬 기준 추가

        // Pageable 객체로 요청된 페이지와 정렬 조건에 맞게 조회
        Page<Post> posts = postRepository.findAll(spec, pageable);

        // 게시글을 DTO로 변환하여 반환
        return posts.map(GetListPostRespDto::from);
    }

}

