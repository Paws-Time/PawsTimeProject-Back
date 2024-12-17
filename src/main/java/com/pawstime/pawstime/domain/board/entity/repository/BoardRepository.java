package com.pawstime.pawstime.domain.board.entity.repository;

import com.pawstime.pawstime.domain.board.entity.Board;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

  @Query("SELECT b FROM Board b WHERE b.title = :title and b.isDelete = false")
  Board findByTitleQuery(String title);

}
