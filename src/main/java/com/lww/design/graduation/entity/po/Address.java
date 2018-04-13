package com.lww.design.graduation.entity.po;

import lombok.Data;

@Data
public class Address {
    private Long id;

    private Long userId;

    private String name;

    private String phone;

    private String cityCode;

    private String details;

    private Integer isDefault;

    private String province;

    private String city;

    private String district;

    private String districtCode;

    private String provinceCode;

}