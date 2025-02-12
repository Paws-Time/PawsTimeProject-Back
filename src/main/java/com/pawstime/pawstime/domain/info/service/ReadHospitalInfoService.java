package com.pawstime.pawstime.domain.info.service;

import com.pawstime.pawstime.domain.info.entity.HospitalInfo;
import com.pawstime.pawstime.domain.info.entity.repository.HospitalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadHospitalInfoService {

  private final HospitalInfoRepository infoRepository;

  public Page<HospitalInfo> readAllHospital(Pageable pageable, int addNum) {
    return infoRepository.findAllQuery(pageable, addNum);
  }
}
