package com.pawstime.pawstime.domain.info.facade;

import com.pawstime.pawstime.domain.info.dto.resp.GetHospitalInfoRespDto;
import com.pawstime.pawstime.domain.info.service.ReadHospitalInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Component
@RequiredArgsConstructor
public class InfoFacade {

    private final ReadHospitalInfoService readHospitalInfoService;

    public Page<GetHospitalInfoRespDto> readHospitalInfo(int pageNo, int pageSize, String sortBy, String direcrion, int addNum) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.fromString(direcrion), sortBy));

        return readHospitalInfoService.readAllHospital(pageable, addNum).map(GetHospitalInfoRespDto::from);
    }
}