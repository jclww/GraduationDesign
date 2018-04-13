package com.lww.design.graduation.service.city;

import com.lww.design.graduation.entity.po.City;

import java.util.List;

public interface CityService {
    List<City> getCityByParentCode(String parentCode);
}
