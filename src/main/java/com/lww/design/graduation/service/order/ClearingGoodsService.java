package com.lww.design.graduation.service.order;

import com.lww.design.graduation.entity.po.ClearingGoods;
import com.lww.design.graduation.entity.vo.goods.GoodsDetailVO;

import java.util.List;

public interface ClearingGoodsService {
    List<GoodsDetailVO> getGoodsInfo(List<ClearingGoods> clearingGoodsList);
}
