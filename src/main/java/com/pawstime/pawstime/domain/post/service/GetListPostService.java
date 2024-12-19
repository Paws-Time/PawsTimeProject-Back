package com.pawstime.pawstime.domain.post.service;

import com.pawstime.pawstime.domain.post.dto.resp.GetListPostRespDto;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GetListPostService {
    private final PostRepository postRepository;


    public Page<GetListPostRespDto> getPostList(Pageable pageable, String searchKeyword, String sortBy, Long boardId) {
        Page<Post> posts;

        // 게시판 ID가 주어진 경우, 게시판 ID로 필터링 (삭제되지 않은 게시글만)
        if (boardId != null) {
            posts = postRepository.findByBoard_boardIdAndIsDeleteFalse(boardId, pageable);  // 삭제되지 않은 게시글만 조회
        } else {
            // 검색어가 있는 경우, 제목과 내용에서 검색 (삭제되지 않은 게시글만)
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                posts = postRepository.findByTitleContainingOrContentContainingAndIsDeleteFalse(
                        searchKeyword, searchKeyword, pageable);  // 삭제되지 않은 게시글만 조회
            } else {
                // 정렬 기준에 맞춰 게시글 조회 (삭제되지 않은 게시글만)
                switch (sortBy) {
                    case "views":
                        posts = postRepository.findAllByIsDeleteFalseOrderByViewsDesc(pageable);  // 조회수 내림차순
                        break;
                    case "title":
                        posts = postRepository.findAllByIsDeleteFalseOrderByTitleAsc(pageable);  // 가나다순
                        break;
                    case "latest":
                    default:
                        posts = postRepository.findAllByIsDeleteFalseOrderByCreatedAtDesc(pageable);  // 최신순
                        break;
                }
            }
        }

        // 엔티티(Post)에서 DTO(GetListPostRespDto)로 변환하여 반환
        return posts.map(post -> GetListPostRespDto.builder()
                .id(post.getPostId())
                .title(post.getTitle())
                .contentPreview(post.getContent().length() > 100
                        ? post.getContent().substring(0, 100) + "..."
                        : post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .views(post.getViews())
                .likesCount(post.getLikesCount())
                .category(post.getCategory().name())
                .build());
    }
}


