package com.lww.design.graduation.entity.vo.goods;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsSkuInfoVO {
    private String id;
    private Integer stock;
    private BigDecimal price;
    private Long skuId;
}
