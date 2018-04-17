package com.lww.design.graduation.mapper;

import com.lww.design.graduation.entity.po.SubOrder;

import java.util.List;

public interface SubOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SubOrder record);

    int insertSelective(SubOrder record);

    SubOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubOrder record);

    int updateByPrimaryKey(SubOrder record);

    List<SubOrder> getSubOrderByOrderId(List<String> orderIds);
}