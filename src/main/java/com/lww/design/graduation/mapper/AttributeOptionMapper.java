package com.lww.design.graduation.mapper;

import com.lww.design.graduation.entity.po.AttributeOption;

public interface AttributeOptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AttributeOption record);

    int insertSelective(AttributeOption record);

    AttributeOption selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AttributeOption record);

    int updateByPrimaryKey(AttributeOption record);
}