package com.lww.design.graduation.mapper;

import com.lww.design.graduation.entity.po.GoodsSpu;

public interface GoodsSpuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsSpu record);

    int insertSelective(GoodsSpu record);

    GoodsSpu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsSpu record);

    int updateByPrimaryKey(GoodsSpu record);
}