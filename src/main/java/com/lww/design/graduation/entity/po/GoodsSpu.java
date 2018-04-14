package com.lww.design.graduation.entity.po;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsSpu {
    private Long id;

    private String name;

    private Integer categoryId;

    private String details;

    private BigDecimal priceBottom;

    private BigDecimal priceTop;

    private Integer commentCount;

}