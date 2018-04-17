package com.lww.design.graduation.entity.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {
    private Long id;

    private Long userId;

    private String orderId;

    private Long addressId;

    private BigDecimal sumPrice;

    private Integer status;

    private Date createdAt;

    private Date modifiedAt;

    private Date deletedAt;

    private BigDecimal deliveryFree;
}