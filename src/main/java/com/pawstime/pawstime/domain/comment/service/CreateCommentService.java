package com.pawstime.pawstime.domain.comment.service;

import com.pawstime.pawstime.domain.comment.dto.resp.CreateCommentRespDto;
import com.pawstime.pawstime.domain.comment.entity.Comment;
import com.pawstime.pawstime.domain.comment.entity.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCommentService {

    private final CommentRepository commentRepository;

    public Comment createComment(Comment comment) {
        comment.getFormattedCreatedAt();
        return commentRepository.save(comment);
    }
}