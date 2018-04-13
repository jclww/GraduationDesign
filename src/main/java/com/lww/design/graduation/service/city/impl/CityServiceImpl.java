package com.lww.design.graduation.service.city.impl;

import com.lww.design.graduation.entity.po.City;
import com.lww.design.graduation.mapper.CityMapper;
import com.lww.design.graduation.service.city.CityService;
import com.lww.design.graduation.utils.OrikaBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class CityServiceImpl implements CityService {
    @Resource
    private CityMapper cityMapper;
    @Resource
    private OrikaBeanUtil orikaBeanUtil;


    @Override
    public List<City> getCityByParentCode(String parentCode) {
        log.info("getCityByParentCode parentCode:{}", parentCode);
        if (StringUtils.isBlank(parentCode)) {
            parentCode = "0";
        }
        return cityMapper.getCityByParentCode(parentCode);
    }
}
