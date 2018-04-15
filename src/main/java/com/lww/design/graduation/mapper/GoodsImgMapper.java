package com.lww.design.graduation.mapper;

import com.lww.design.graduation.entity.po.GoodsImg;

import java.util.List;

public interface GoodsImgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsImg record);

    int insertSelective(GoodsImg record);

    GoodsImg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsImg record);

    int updateByPrimaryKey(GoodsImg record);

    List<GoodsImg> getBySpuId(Long spu);

    List<GoodsImg> getBySpuIdList(List<Long> spuList);
}