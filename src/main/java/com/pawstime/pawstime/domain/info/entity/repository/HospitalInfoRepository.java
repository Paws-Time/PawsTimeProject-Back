package com.pawstime.pawstime.domain.info.entity.repository;

import com.pawstime.pawstime.domain.info.entity.HospitalInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HospitalInfoRepository extends JpaRepository<HospitalInfo, Long> {

  @Query("SELECT h FROM HospitalInfo h WHERE h.addNum = :addNum")
  Page<HospitalInfo> findAllQuery(Pageable pageable, int addNum);
}
