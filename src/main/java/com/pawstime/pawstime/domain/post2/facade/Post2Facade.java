package com.pawstime.pawstime.domain.post2.facade;

import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.post.dto.req.CreatePostReqDto;
import com.pawstime.pawstime.domain.post2.dto.req.CreatePost2ReqDto;
import com.pawstime.pawstime.domain.post2.service.ReadPost2Service;
import com.pawstime.pawstime.global.exception.InvalidException;
import com.pawstime.pawstime.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@Component
@RequiredArgsConstructor
public class Post2Facade {

    private final ReadPost2Service readPost2Service;

    public void createPost2(CreatePost2ReqDto req) {
        if (req.boardId() == null || req.title() == null || req.content() == null) {
            throw new InvalidException("필수 입력값이 누락되었습니다.");
        }

        Board board = readPost2Service.findBoardById(req.boardId());
        if (board == null || board.isDelete()) {
            throw new NotFoundException("유효하지 않은 게시판입니다.");
        }
    }
}