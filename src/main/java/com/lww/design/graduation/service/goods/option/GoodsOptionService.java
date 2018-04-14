package com.lww.design.graduation.service.goods.option;

import com.lww.design.graduation.entity.po.GoodsOption;
import com.lww.design.graduation.entity.vo.goods.GoodsAttributeVO;

import java.util.List;

public interface GoodsOptionService {
    List<GoodsAttributeVO> getGoodsAttributeBySpuId(Long spuId);

    List<GoodsOption> getBySkuList(List<Long> skuIdList);
}
