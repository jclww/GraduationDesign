package com.lww.design.graduation.entity.vo;

import com.lww.design.graduation.entity.vo.goods.SkuAttributeVO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SubOrderInfoVO {
    private Long skuId;
    private Long spuId;
    private String name;
    private String imgUrl;
    private String orderId;
    private Integer count;
    private BigDecimal price;
    private List<SkuAttributeVO> attributes;
}
