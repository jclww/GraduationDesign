package com.lww.design.graduation.entity.vo.goods;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GoodsDetailVO {
    private Long cartId;
    private Long spuId;
    private String spuName;
    private String imgUrl;
    private String skuName;
    private Integer stock;
    private BigDecimal price;
    private Integer count;
    private BigDecimal totalPrice;
    private Long skuId;
    List<SkuAttributeVO> attributes;
}
