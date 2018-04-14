package com.lww.design.graduation.mapper;

import com.lww.design.graduation.entity.po.GoodsOption;

import java.util.List;

public interface GoodsOptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsOption record);

    int insertSelective(GoodsOption record);

    GoodsOption selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsOption record);

    int updateByPrimaryKey(GoodsOption record);

    List<GoodsOption> getBySkuList(List<Long> skuIdList);
}