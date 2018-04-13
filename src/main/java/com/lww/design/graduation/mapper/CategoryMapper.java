package com.lww.design.graduation.mapper;

import com.lww.design.graduation.entity.po.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> queryByParentId(List<Integer> categortIdList);

    List<Category> getRoot();
}