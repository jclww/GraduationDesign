package com.lww.design.graduation.service.cart;

import com.lww.design.graduation.entity.po.Cart;
import com.lww.design.graduation.entity.vo.goods.GoodsDetailVO;

import java.util.List;

public interface ShopCartService {
    Integer addGoods(Cart cart, Long userId);

    List<GoodsDetailVO> getAllGoods(Long userId);

    Integer deleteCartById(Long cartId);

    Integer deleteBySkuIdAndUser(List<Long> skuIdList, Long userId);
}
