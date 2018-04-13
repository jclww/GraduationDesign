package com.lww.design.graduation.entity.vo.address;

import lombok.Data;

@Data
public class AddressVO {
    private Long id;

    private Long userId;

    private String name;
    private String province;
    private String city;
    private String district;

    private String phone;

    private Integer cityCode;

    private String details;

    private Integer isDefault;
}