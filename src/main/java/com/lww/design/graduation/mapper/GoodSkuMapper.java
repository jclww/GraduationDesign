package com.lww.design.graduation.mapper;

import com.lww.design.graduation.entity.po.GoodSku;

public interface GoodSkuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodSku record);

    int insertSelective(GoodSku record);

    GoodSku selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodSku record);

    int updateByPrimaryKey(GoodSku record);
}