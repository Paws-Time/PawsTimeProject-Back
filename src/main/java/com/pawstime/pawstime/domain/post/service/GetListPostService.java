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


    public Page<GetListPostRespDto> getPostList(Long boardId, String keyword, Pageable pageable) {
        // 기본적으로 삭제되지 않은 게시글만 조회
        Specification<Post> spec = Specification.where(PostSpecification.isNotDeleted())
                .and(PostSpecification.hasKeyword(keyword))
                .and(PostSpecification.belongsToBoard(boardId))
                .and(PostSpecification.boardIsNotDeleted()); // 게시판이 삭제되지 않았는지 확인

        // Pageable로 요청된 페이지와 정렬 조건에 맞게 조회
        Page<Post> posts = postRepository.findAll(spec, pageable);

        // 게시글을 DTO로 변환하여 반환
        return posts.map(GetListPostRespDto::from);
    }
}

