package com.lww.design.graduation.service.order;

import com.lww.design.graduation.entity.po.SubOrder;

import java.util.List;

public interface OrderService {
    Long createOrder(List<SubOrder> subOrders, Long addressId, Long userId, Boolean clearCart);
}
