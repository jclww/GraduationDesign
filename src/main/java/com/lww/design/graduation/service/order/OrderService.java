package com.lww.design.graduation.service.order;

import com.lww.design.graduation.entity.po.SubOrder;
import com.lww.design.graduation.entity.vo.OrderInfoVO;

import java.util.List;

public interface OrderService {
    Long createOrder(List<SubOrder> subOrders, Long addressId, Long userId, Boolean clearCart);

    String payOrder(Long orderId, Long userId);

    List<OrderInfoVO> getOrderInfoByUserId(Long id);

    Integer deliver(Long orderId);

    Integer receiveStatus(Long orderId);
}
