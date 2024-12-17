package com.pawstime.pawstime.domain.post.facade;

import com.pawstime.pawstime.domain.board.dto.req.CreateBoardReqDto;
import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.post.dto.req.CreatePostReqDto;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.service.CreatePostService;
import com.pawstime.pawstime.domain.post.service.ReadPostService;
import com.pawstime.pawstime.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Component
@RequiredArgsConstructor
public class PostFacade {
  private final ReadPostService readPostService;
  private final CreatePostService createPostService;

  public void createPost(CreatePostReqDto req){

    User user = readPostService.findUserById(req.userId());
    if(user == null){
      throw new RuntimeException("해당 User ID는 존재하지 않습니다.");
    }

    Board board = readPostService.findBoardById(req.boardId());
    if (board == null){
      throw new RuntimeException("해당 Board ID는 존재하지 않습니다.");
    }

    //DTO를 엔티티로 변환
    Post post = req.toEntity(user, board);
    createPostService.createPost(post);
  }

}
