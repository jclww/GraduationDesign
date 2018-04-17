package com.lww.design.graduation.entity.po;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SubOrder {
    private Long id;

    private String orderId;

    private Long skuId;

    private Integer count;

    private BigDecimal price;

    private BigDecimal sum;
}