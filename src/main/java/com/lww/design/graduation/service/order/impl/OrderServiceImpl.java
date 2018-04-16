package com.lww.design.graduation.service.order.impl;

import com.lww.design.graduation.common.exception.BizException;
import com.lww.design.graduation.entity.po.GoodsSku;
import com.lww.design.graduation.entity.po.Order;
import com.lww.design.graduation.entity.po.SubOrder;
import com.lww.design.graduation.mapper.OrderMapper;
import com.lww.design.graduation.mapper.SubOrderMapper;
import com.lww.design.graduation.service.cart.ShopCartService;
import com.lww.design.graduation.service.goods.sku.GoodsSKUService;
import com.lww.design.graduation.service.order.OrderService;
import com.lww.design.graduation.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private SubOrderMapper subOrderMapper;
    @Resource
    private GoodsSKUService goodsSKUService;
    @Resource
    private ShopCartService shopCartService;
    @Resource
    private IdWorker idWorker;

    @Override
    @Transactional
    public Long createOrder(List<SubOrder> subOrders, Long addressId, Long userId, Boolean clearCart) {
        List<Long> skuIdList = subOrders.stream().map(SubOrder::getSkuId).collect(Collectors.toList());
        List<GoodsSku> skuList = goodsSKUService.getBySku(skuIdList);
        Order order = new Order();
        BigDecimal sumPrice = subOrders.stream().map(subOrder -> {
            Optional<GoodsSku> goodsSku = skuList.stream().filter(sku -> sku.getId().equals(subOrder.getSkuId())).findFirst();
            if (!goodsSku.isPresent()) {
                throw new BizException("error");
            }
            return goodsSku.get().getPrice().multiply(new BigDecimal(subOrder.getCount()));
        }).reduce(BigDecimal.ZERO,BigDecimal::add);
        long orderId = idWorker.nextId();
        order.setOrderId(String.valueOf(orderId));
        order.setAddressId(addressId);
        order.setUserId(userId);
        order.setSumPrice(sumPrice);
        // 插入订单
        int insertSuccess = orderMapper.insertSelective(order);
        log.info("insertSuccess:{} orderId:{}", insertSuccess, orderId);
        int insertSubOrderSuccess = subOrders.size();
        // 插入子订单
        subOrders.forEach(subOrder -> {
            subOrder.setOrderId(String.valueOf(orderId));
            Optional<GoodsSku> goodsSku = skuList.stream().filter(sku -> sku.getId().equals(subOrder.getSkuId())).findFirst();
            if (!goodsSku.isPresent()) {
                throw new BizException("error");
            }
            subOrder.setPrice(goodsSku.get().getPrice());
            subOrder.setSum(subOrder.getPrice().multiply(new BigDecimal(subOrder.getCount())));
            int insertSub = subOrderMapper.insertSelective(subOrder);
            log.info("insertSub:{}",insertSub);
        });
        log.info("insertSubOrderSuccess:{}", insertSubOrderSuccess);
        // 扣除库存
        skuList.forEach(sku ->{
            Optional<SubOrder> subOrder = subOrders.stream().filter(subOrderItem -> sku.getId().equals(subOrderItem.getSkuId())).findFirst();
            if (!subOrder.isPresent()) {
                throw new BizException("error");
            }
            sku.setStock(sku.getStock()- subOrder.get().getCount());
            int updateStock = goodsSKUService.updateById(sku);
            log.info("updateStock:{} updateAfter stockNum:{}",updateStock,sku.getStock());
        });
        // 删除购物车sku
        if(clearCart) {
            int deleteCart = shopCartService.deleteBySkuIdAndUser(skuIdList, userId);
            log.info("deleteCart:{}", deleteCart);
        }
        return orderId;
    }
}
