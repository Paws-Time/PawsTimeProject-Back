package com.pawstime.pawstime.domain.post2.service;

import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.board.entity.repository.BoardRepository;
import com.pawstime.pawstime.domain.post2.entity.Post2;
import com.pawstime.pawstime.domain.post2.entity.repository.Post2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadPost2Service {

    private final Post2Repository post2Repository;
    private final BoardRepository boardRepository;

    public Post2 findPost2Id(Long post2Id){
        return post2Repository.findById(post2Id)
                .orElse(null);
    }

    // Board를 ID로 조회하는 메서드
    public Board findBoardById(Long boardId) {
        return boardRepository.findById(boardId)
                .orElse(null);
    }
}
