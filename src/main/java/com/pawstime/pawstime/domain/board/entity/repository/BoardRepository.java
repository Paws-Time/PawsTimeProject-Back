package com.pawstime.pawstime.domain.board.entity.repository;

import com.pawstime.pawstime.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

  @Query("SELECT b FROM Board b WHERE b.title = :title AND b.isDelete = false")
  Board findByTitleQuery(String title);


  @Query("SELECT b FROM Board b WHERE b.boardId = :boardId AND b.isDelete = false")
  Board findByIdQuery(Long boardId);

  @Query("SELECT b FROM Board b WHERE b.isDelete = false")
  Page<Board> findAllQuery(Pageable pageable);

}
