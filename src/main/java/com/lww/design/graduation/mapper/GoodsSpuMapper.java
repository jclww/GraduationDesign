package com.lww.design.graduation.mapper;

import com.lww.design.graduation.entity.dao.goods.GoodsSO;
import com.lww.design.graduation.entity.po.GoodsSku;
import com.lww.design.graduation.entity.po.GoodsSpu;

import java.util.List;

public interface GoodsSpuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsSpu record);

    int insertSelective(GoodsSpu record);

    GoodsSpu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsSpu record);

    int updateByPrimaryKey(GoodsSpu record);

    List<GoodsSpu> getByIdList(List<Long> spuList);

    List<GoodsSpu> search(GoodsSO goodsSO);
}