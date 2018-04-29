package com.lww.design.graduation.entity.vo;

import com.lww.design.graduation.entity.po.base.SearchBase;
import lombok.Data;

@Data
public class GoodsSearchRequestVO extends SearchBase{
    private String name;
    private Integer categoryId;
}
