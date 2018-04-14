package com.lww.design.graduation.mapper;

import com.lww.design.graduation.entity.po.GoodsImg;

public interface GoodsImgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsImg record);

    int insertSelective(GoodsImg record);

    GoodsImg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsImg record);

    int updateByPrimaryKey(GoodsImg record);
}