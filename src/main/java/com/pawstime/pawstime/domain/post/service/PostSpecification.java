package com.pawstime.pawstime.domain.post.service;

import com.pawstime.pawstime.domain.post.dto.resp.GetListPostRespDto;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public class PostSpecification {

    public static Specification<Post> isNotDeleted() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isFalse(root.get("isDelete"));
    }

    public static Specification<Post> boardIsNotDeleted() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isFalse(root.get("board").get("isDelete"));
    }

    public static Specification<Post> hasKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword == null || keyword.trim().isEmpty()) {
                return criteriaBuilder.conjunction(); // 조건 없음
            }
            String likePattern = "%" + keyword.toLowerCase() + "%";
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), likePattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("content")), likePattern)
            );
        };
    }

    public static Specification<Post> belongsToBoard(Long boardId) {
        return (root, query, criteriaBuilder) -> {
            if (boardId == null) {
                return criteriaBuilder.conjunction(); // boardId가 없으면 항상 참을 반환 (조건 없음)
            }
            return criteriaBuilder.equal(root.get("board").get("id"), boardId);
        };
    }

    public static Specification<Post> orderBy(String sortBy, String direction) {
        return (root, query, criteriaBuilder) -> {
            if ("views".equalsIgnoreCase(sortBy)) {
                query.orderBy(direction.equalsIgnoreCase("asc") ? criteriaBuilder.asc(root.get("views")) : criteriaBuilder.desc(root.get("views")));
            } else if ("likesCount".equalsIgnoreCase(sortBy)) {  // likes_count -> likesCount로 변경
                query.orderBy(direction.equalsIgnoreCase("asc") ? criteriaBuilder.asc(root.get("likesCount")) : criteriaBuilder.desc(root.get("likesCount")));
            } else if ("title".equalsIgnoreCase(sortBy)) {
                query.orderBy(direction.equalsIgnoreCase("asc") ? criteriaBuilder.asc(root.get("title")) : criteriaBuilder.desc(root.get("title")));
            } else if ("createdAt".equalsIgnoreCase(sortBy)) {
                query.orderBy(direction.equalsIgnoreCase("asc") ? criteriaBuilder.asc(root.get("createdAt")) : criteriaBuilder.desc(root.get("createdAt")));
            } else {
                query.orderBy(criteriaBuilder.desc(root.get("createdAt"))); // 기본값: createdAt 내림차순
            }

            return criteriaBuilder.conjunction(); // 기본적으로 조건이 없으면 항상 참을 반환
        };
    }

}
