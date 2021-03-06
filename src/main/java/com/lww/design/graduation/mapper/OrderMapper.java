package com.lww.design.graduation.mapper;

import com.lww.design.graduation.entity.po.Order;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Order getByOrderId(Long orderId);

    List<Order> getByUserId(Long userId);
}