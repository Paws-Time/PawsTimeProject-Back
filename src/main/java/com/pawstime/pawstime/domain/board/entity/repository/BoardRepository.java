package com.pawstime.pawstime.domain.board.entity.repository;

import com.pawstime.pawstime.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
