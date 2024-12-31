package com.pawstime.pawstime.domain.info.service;

import com.pawstime.pawstime.domain.info.entity.Info;
import com.pawstime.pawstime.domain.info.entity.repository.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadHospitalInfoService {

  private final InfoRepository infoRepository;

  public Page<Info> readAllHospital(Pageable pageable, int addNum) {
    return infoRepository.findAllQuery(pageable, addNum);
  }
}
