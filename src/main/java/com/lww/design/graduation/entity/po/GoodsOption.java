package com.lww.design.graduation.entity.po;

import lombok.Data;

@Data
public class GoodsOption {
    private Long id;

    private Long skuId;

    private Integer attributeId;

    private Integer optionId;

    private String attributeName;

    private String optionName;
}