package com.pawstime.pawstime.domain.post.service;

import com.pawstime.pawstime.domain.post.dto.resp.GetListPostRespDto;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GetListPostService {

    private final PostRepository postRepository;

    public Page<GetListPostRespDto> getPostList(Long boardId, String keyword, String sort, Pageable pageable) {
        // sort 값이 null이거나 유효하지 않으면 기본값으로 'createdAt'을 사용
        if (sort == null || (!sort.equals("createdAt") && !sort.equals("views") && !sort.equals("title"))) {
            sort = "createdAt";  // 기본값 설정
        }

        // 동적 쿼리 호출 (isDelete가 true인 게시글은 제외)
        Page<Post> posts = postRepository.findByBoardIdAndKeywordAndIsDeleted(boardId, keyword, sort, pageable);

        // Page<Post>를 Page<GetListPostRespDto>로 변환하여 반환
        return posts.map(GetListPostRespDto::from);
    }

}




