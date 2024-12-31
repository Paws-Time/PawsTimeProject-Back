package com.pawstime.pawstime.domain.info.entity.repository;

import com.pawstime.pawstime.domain.info.entity.Info;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InfoRepository extends JpaRepository<Info, Long> {

  @Query("SELECT i FROM Info i WHERE i.addNum = :addNum")
  Page<Info> findAllQuery(Pageable pageable, int addNum);
}
