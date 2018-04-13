package com.lww.design.graduation.controller.common;

import com.lww.design.graduation.entity.po.City;
import com.lww.design.graduation.service.city.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/common")
public class CityController {
    @Resource
    private CityService cityService;

    @RequestMapping(value = "/city")
    @ResponseBody
    public List<City> getPngImage(String code) {
        return cityService.getCityByParentCode(code);
    }
}
