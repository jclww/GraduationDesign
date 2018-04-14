package com.lww.design.graduation.entity.vo.goods;

import lombok.Data;

import java.util.List;

@Data
public class GoodsAttributeVO {
    private String attributeName;
    private Integer attributeId;
    private List<GoodsOptionVO> optionList;
}
