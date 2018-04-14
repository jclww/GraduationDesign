package com.lww.design.graduation.mapper;

import com.lww.design.graduation.entity.po.GoodsSku;

import java.util.List;

public interface GoodsSkuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsSku record);

    int insertSelective(GoodsSku record);

    GoodsSku selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsSku record);

    int updateByPrimaryKey(GoodsSku record);

    List<GoodsSku> getBySpu(Long spu);
}