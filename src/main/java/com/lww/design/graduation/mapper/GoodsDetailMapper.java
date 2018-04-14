package com.lww.design.graduation.mapper;

import com.lww.design.graduation.entity.po.GoodsDetail;

public interface GoodsDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsDetail record);

    int insertSelective(GoodsDetail record);

    GoodsDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsDetail record);

    int updateByPrimaryKey(GoodsDetail record);
}