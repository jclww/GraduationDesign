package com.lww.design.graduation.mapper;

import com.lww.design.graduation.entity.po.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    List<Cart> getByUser(Long userId);

    List<Cart> query(Cart cart);

    Integer deleteBySkuIdAndUser(@Param("skuIdList") List<Long> skuIdList,@Param("userId") Long userId);
}