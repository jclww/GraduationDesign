package com.lww.design.graduation.entity.po;

import lombok.Data;

@Data
public class GoodsImg {
    private Long id;

    private Long spuId;

    private String smallImgUrl;

    private String midImgUrl;

    private String bigImgUrl;

    private Integer sort;
}