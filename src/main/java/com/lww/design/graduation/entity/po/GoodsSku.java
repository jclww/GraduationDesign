package com.lww.design.graduation.entity.po;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class GoodsSku {
    private Long id;

    private String name;

    private Long spuId;

    private BigDecimal price;

    private Integer stock;
}