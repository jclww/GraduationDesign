package com.lww.design.graduation.service.order.impl;

import com.lww.design.graduation.common.exception.BizException;
import com.lww.design.graduation.entity.po.*;
import com.lww.design.graduation.entity.vo.goods.GoodsDetailVO;
import com.lww.design.graduation.entity.vo.goods.SkuAttributeVO;
import com.lww.design.graduation.service.goods.img.GoodsImgService;
import com.lww.design.graduation.service.goods.option.GoodsOptionService;
import com.lww.design.graduation.service.goods.sku.GoodsSKUService;
import com.lww.design.graduation.service.goods.spu.GoodsSPUService;
import com.lww.design.graduation.service.order.ClearingGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClearingGoodsServiceImpl implements ClearingGoodsService {

    @Resource
    private GoodsSKUService goodsSKUService;
    @Resource
    private GoodsSPUService goodsSPUService;
    @Resource
    private GoodsOptionService goodsOptionService;
    @Resource
    private GoodsImgService goodsImgService;

    @Override
    public List<GoodsDetailVO> getGoodsInfo(List<ClearingGoods> clearingGoodsList) {
        List<Long> skuList = clearingGoodsList.stream().map(ClearingGoods::getSkuId).collect(Collectors.toList());
        List<GoodsSku> goodsSkuList = goodsSKUService.getBySku(skuList);
        List<GoodsOption> goodsOptions = goodsOptionService.getBySkuList(skuList);

        List<Long> spuList = goodsSkuList.stream().map(GoodsSku::getSpuId).distinct().collect(Collectors.toList());
        List<GoodsSpu> goodsSpuList = goodsSPUService.getByIdList(spuList);
        List<GoodsImg> goodsImgList = goodsImgService.getBySpuIdList(spuList);

        List<GoodsDetailVO> goodsDetailVOList = goodsSkuList.stream().map(goodsSku -> {
            GoodsDetailVO detailVO = new GoodsDetailVO();
            detailVO.setSkuId(goodsSku.getId());
            detailVO.setSkuName(goodsSku.getName());
            detailVO.setPrice(goodsSku.getPrice());
            detailVO.setStock(goodsSku.getStock());
            Optional<ClearingGoods> clearingGoods = clearingGoodsList.stream().filter(c -> c.getSkuId().equals(goodsSku.getId())).findFirst();
            Optional<GoodsSpu> goodsSpu = goodsSpuList.stream().filter(g -> g.getId().equals(goodsSku.getSpuId())).findFirst();
            Optional<GoodsImg> goodsImg = goodsImgList.stream().filter(img -> img.getSpuId().equals(goodsSku.getSpuId())).findFirst();
            if (!goodsSpu.isPresent() || !clearingGoods.isPresent() || !goodsImg.isPresent()) {
                throw new BizException("系统异常");
            }
            detailVO.setCount(clearingGoods.get().getCount());
//            detailVO.setCartId(cart.get().getId());
            detailVO.setTotalPrice(goodsSku.getPrice().multiply(new BigDecimal(clearingGoods.get().getCount())));
            detailVO.setSpuId(goodsSpu.get().getId());
            detailVO.setSpuName(goodsSpu.get().getName());
            detailVO.setImgUrl(goodsImg.get().getSmallImgUrl());
            List<SkuAttributeVO> skuAttributeVOList = goodsOptions.stream()
                    .filter(goodsOption -> goodsOption.getSkuId().equals(goodsSku.getId()))
                    .map(goodsOption -> {
                        SkuAttributeVO skuAttributeVO = new SkuAttributeVO();
                        skuAttributeVO.setAttributeName(goodsOption.getAttributeName());
                        skuAttributeVO.setOptionName(goodsOption.getOptionName());
                        return skuAttributeVO;
                    }).collect(Collectors.toList());
            detailVO.setAttributes(skuAttributeVOList);
            return detailVO;
        }).collect(Collectors.toList());
        return goodsDetailVOList;
    }
}
