package com.pawstime.pawstime.domain.post.service;

import com.pawstime.pawstime.domain.post.dto.resp.GetListPostRespDto;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GetListPostService {

    private final PostRepository postRepository;

    public Page<GetListPostRespDto> getPostList(Long boardId, String keyword, String sort, Pageable pageable) {
        // 게시판 ID가 null일 경우 전체 게시글을 조회
        if (boardId == null) {
            // boardId가 null일 경우 false 값을 전달
            return postRepository.findByBoardIdAndKeywordAndIsDeleted(null, keyword, false, pageable)
                    .map(GetListPostRespDto::from);
        }

        // 게시판 ID가 있을 경우 해당 게시판의 게시글을 조회
        return postRepository.findByBoardIdAndKeywordAndIsDeleted(boardId, keyword, false, pageable)
                .map(GetListPostRespDto::from);
    }

}






