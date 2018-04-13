package com.lww.design.graduation.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class CategoryVO {

    private Integer id;

    private String name;

    private Integer parentId;

    private String imgUrl;

    private List<CategoryVO> children;
}
