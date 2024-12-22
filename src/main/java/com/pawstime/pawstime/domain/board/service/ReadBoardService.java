package com.pawstime.pawstime.domain.board.service;

import com.pawstime.pawstime.domain.board.dto.resp.GetBoardRespDto;
import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.board.entity.repository.BoardRepository;
import com.pawstime.pawstime.domain.post.entity.Post;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadBoardService {

  private final BoardRepository boardRepository;

  public Board findByTitle(String title) {
    return boardRepository.findByTitleQuery(title);
  }

  public Board findById(Long boardId) {
    return boardRepository.findById(boardId).orElse(null);
  }

  public Board findByIdQuery(Long boardId) {
    return boardRepository.findByIdQuery(boardId);
  }

  public Page<Board> getBoardList(Pageable pageable) {
    return boardRepository.findAllQuery(pageable);
  }

}
