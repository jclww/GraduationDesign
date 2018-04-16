package com.lww.design.graduation.service.goods.sku;

import com.lww.design.graduation.entity.po.GoodsSku;
import com.lww.design.graduation.entity.vo.goods.GoodsSkuInfoVO;

import java.util.List;

public interface GoodsSKUService {
    List<GoodsSkuInfoVO> getGoodsSkuInfoBySpuId(Long spu);

    List<GoodsSku> getSkuBySpu(Long spu);

    List<GoodsSku> getBySku(List<Long> skuList);

    Integer updateById(GoodsSku sku);
}
