package com.lww.design.graduation.entity.po;

import lombok.Data;

@Data
public class Cart {
    private Long id;

    private Long userId;

    private Long skuId;

    private Integer status;

    private Integer count;

}