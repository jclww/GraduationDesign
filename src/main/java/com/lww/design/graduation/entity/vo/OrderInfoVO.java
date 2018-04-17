package com.lww.design.graduation.entity.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderInfoVO {
    private String orderId;
    private BigDecimal sumPrice;
    private Integer status;
    private Date createdAt;
    private List<SubOrderInfoVO> subOrders;
}
