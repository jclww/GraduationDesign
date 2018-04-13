package com.lww.design.graduation.entity.po;

import lombok.Data;

@Data
public class Category {
    private Integer id;

    private String name;

    private Integer parentId;

    private String imgUrl;
}