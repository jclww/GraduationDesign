package com.lww.design.graduation.service.order.impl;

import com.lww.design.graduation.common.enums.OrderEnums;
import com.lww.design.graduation.common.exception.BizException;
import com.lww.design.graduation.entity.po.*;
import com.lww.design.graduation.entity.po.user.User;
import com.lww.design.graduation.entity.vo.OrderInfoVO;
import com.lww.design.graduation.entity.vo.SubOrderInfoVO;
import com.lww.design.graduation.entity.vo.goods.SkuAttributeVO;
import com.lww.design.graduation.mapper.OrderMapper;
import com.lww.design.graduation.mapper.SubOrderMapper;
import com.lww.design.graduation.service.cart.ShopCartService;
import com.lww.design.graduation.service.goods.img.GoodsImgService;
import com.lww.design.graduation.service.goods.option.GoodsOptionService;
import com.lww.design.graduation.service.goods.sku.GoodsSKUService;
import com.lww.design.graduation.service.order.OrderService;
import com.lww.design.graduation.service.permission.UserService;
import com.lww.design.graduation.utils.IdWorker;
import com.lww.design.graduation.utils.OrikaBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    private GoodsOptionService goodsOptionService;
    @Resource
    private UserService userService;
    @Resource
    private IdWorker idWorker;
    @Resource
    private GoodsImgService goodsImgService;
    @Resource
    private OrikaBeanUtil orikaBeanUtil;

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
        // 校验是否有库存
        skuList.forEach(sku -> {
            Optional<SubOrder> subOrder = subOrders.stream().filter(orders -> orders.getSkuId().equals(sku.getId())).findFirst();
            if (!subOrder.isPresent()) {
                throw new BizException("error");
            }
            if (subOrder.get().getCount() > sku.getStock()) {
                throw new BizException("您来晚了"+sku.getName()+"库存不足，只有" + sku.getStock());
            }
        });
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

    @Override
    @Transactional
    public String payOrder(Long orderId, Long userId) {
        Order order = orderMapper.getByOrderId(orderId);
        if (!order.getStatus().equals(OrderEnums.UNPAID)) {
            return "已经付款了,不需要重复付款～";
        }
        User user = userService.getById(userId);
        if ((user.getMoney().compareTo(order.getSumPrice())) < 0) {
            return "您的余额不足以支付，请充值";
        }
        // 更新用户余额
        user.setMoney(user.getMoney().subtract(order.getSumPrice()));
        int updateUser = userService.updateById(user);
        log.info("updateUser:{}", updateUser);
        // 修改订单状态
        order.setStatus(OrderEnums.UNDELIVER.getCode());
        int updateOrder = orderMapper.updateByPrimaryKeySelective(order);
        log.info("updateOrder:{}", updateOrder);
        return "支付成功，共支付:"+order.getSumPrice()+"元,您的余额:"+user.getMoney()+"元";
    }

    @Override
    public List<OrderInfoVO> getOrderInfoByUserId(Long userId) {
        List<Order> orders = orderMapper.getByUserId(userId);
        if (CollectionUtils.isEmpty(orders)) {
            return new ArrayList<>();
        }
        List<String> orderIds = orders.stream().map(Order::getOrderId).collect(Collectors.toList());
        List<SubOrder> subOrders = subOrderMapper.getSubOrderByOrderId(orderIds);
        List<Long> skuIds = subOrders.stream().map(SubOrder::getSkuId).distinct().collect(Collectors.toList());
        List<GoodsSku> goodsSkus = goodsSKUService.getBySku(skuIds);
        List<GoodsOption> goodsOptions = goodsOptionService.getBySkuList(skuIds);
        List<Long> spuList = goodsSkus.stream().map(GoodsSku::getSpuId).distinct().collect(Collectors.toList());
        List<GoodsImg> goodsImgList = goodsImgService.getBySpuIdList(spuList);

        // 对数据进行整理
        List<OrderInfoVO> orderInfoVOList = orikaBeanUtil.convertList(orders, OrderInfoVO.class);
        List<SubOrderInfoVO> subOrderInfoVOS = orikaBeanUtil.convertList(subOrders, SubOrderInfoVO.class);
        List<SkuAttributeVO> skuAttributeVOList = orikaBeanUtil.convertList(goodsOptions, SkuAttributeVO.class);
        // 为子订单添加属性信息
        subOrderInfoVOS.forEach(subOrderInfoVO -> {
            List<SkuAttributeVO> subOrderAttributes = skuAttributeVOList.stream()
                    .filter(skuAttributeVO -> skuAttributeVO.getSkuId().equals(subOrderInfoVO.getSkuId()))
                    .collect(Collectors.toList());
            // 填充name
            Optional<GoodsSku> goodsSku = goodsSkus.stream().filter(goods -> goods.getId().equals(subOrderInfoVO.getSkuId())).findFirst();
            // 填充图片
            Optional<GoodsImg> goodsImgs = goodsImgList.stream().filter(goodsImg -> goodsImg.getSpuId().equals(goodsSku.get().getSpuId())).findFirst();
            if (!goodsSku.isPresent() || !goodsImgs.isPresent()) {
                throw new BizException("error");
            }
            subOrderInfoVO.setImgUrl(goodsImgs.get().getMidImgUrl());
            subOrderInfoVO.setName(goodsSku.get().getName());
            subOrderInfoVO.setSpuId(goodsSku.get().getSpuId());
            subOrderInfoVO.setAttributes(subOrderAttributes);
        });
        // 为订单添加子订单信息
        orderInfoVOList.forEach(orderInfoVO -> {
            List<SubOrderInfoVO> orderSubs = subOrderInfoVOS.stream()
                    .filter(subOrderInfoVO -> subOrderInfoVO.getOrderId().equals(orderInfoVO.getOrderId()))
                    .collect(Collectors.toList());
            orderInfoVO.setSubOrders(orderSubs);
        });
        return orderInfoVOList;
    }

    @Override
    public Integer deliver(Long orderId) {
        Order order = orderMapper.getByOrderId(orderId);
        if (order == null || !order.getStatus().equals(OrderEnums.UNDELIVER.getCode())) {
            throw new BizException("出错了 订单找不到/订单不是待发货状态");
        }
        order.setStatus(OrderEnums.UNRECEIVE.getCode());
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public Integer receiveStatus(Long orderId) {
        Order order = orderMapper.getByOrderId(orderId);
        if (order == null || !order.getStatus().equals(OrderEnums.UNRECEIVE.getCode())) {
            throw new BizException("出错了 订单找不到/订单不是待收货状态");
        }
        order.setStatus(OrderEnums.UNASSESS.getCode());
        return orderMapper.updateByPrimaryKeySelective(order);
    }
}
