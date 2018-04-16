package com.lww.design.graduation.service.order.impl;

import com.lww.design.graduation.mapper.OrderMapper;
import com.lww.design.graduation.mapper.SubOrderMapper;
import com.lww.design.graduation.service.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    private SubOrderMapper subOrderMapper;

}
