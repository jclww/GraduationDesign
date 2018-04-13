package com.lww.design.graduation.entity.po;

import lombok.Data;

@Data
public class City {
    private Long id;

    private String code;

    private String name;

    private String treePath;

    private String remark;

    private String parentCode;

    private Integer level;
}