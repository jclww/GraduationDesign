package com.lww.design.graduation.service.goods.sku.impl;

import com.lww.design.graduation.common.exception.BizException;
import com.lww.design.graduation.entity.po.GoodsOption;
import com.lww.design.graduation.entity.po.GoodsSku;
import com.lww.design.graduation.entity.vo.goods.GoodsSkuInfoVO;
import com.lww.design.graduation.mapper.GoodsSkuMapper;
import com.lww.design.graduation.service.goods.option.GoodsOptionService;
import com.lww.design.graduation.service.goods.sku.GoodsSKUService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GoodsSKUServiceImpl implements GoodsSKUService {
    @Resource
    private GoodsSkuMapper goodsSkuMapper;
    @Resource
    private GoodsOptionService goodsOptionService;
    @Override
    public List<GoodsSkuInfoVO> getGoodsSkuInfoBySpuId(Long spuId) {
        List<GoodsSku> skuList = getSkuBySpu(spuId);
        List<Long> skuIdList = skuList.stream().map(GoodsSku::getId).collect(Collectors.toList());
        List<GoodsOption> goodsOptions = goodsOptionService.getBySkuList(skuIdList);
        Map<Long, List<GoodsOption>> stringListMap = goodsOptions.stream().collect(Collectors.groupingBy(GoodsOption::getSkuId));
        List<GoodsSkuInfoVO> goodsSkuInfoVOList = stringListMap.entrySet().stream().map(entry -> {
            GoodsSkuInfoVO skuInfoVO = new GoodsSkuInfoVO();
            String id = entry.getValue().stream()
                    .sorted(Comparator.comparing(GoodsOption::getAttributeId))
                    .map(goodsOption -> {
                 return goodsOption.getAttributeId()+":"+goodsOption.getOptionId();
            }).collect(Collectors.joining(";"));
            skuInfoVO.setId(id);
            skuInfoVO.setSkuId(entry.getKey());
            Optional<GoodsSku> goodsSku = skuList.stream().filter(goodSku -> {
                return  goodSku.getId().equals(entry.getKey());
            }).findFirst();
            if (!goodsSku.isPresent()) {
                throw new BizException("error");
            }
            skuInfoVO.setPrice(goodsSku.get().getPrice());
            skuInfoVO.setStock(goodsSku.get().getStock());
            return skuInfoVO;
        }).collect(Collectors.toList());

        return goodsSkuInfoVOList;
    }

    @Override
    public List<GoodsSku> getSkuBySpu(Long spu) {
        return goodsSkuMapper.getBySpu(spu);
    }

    @Override
    public List<GoodsSku> getBySku(List<Long> skuList) {
        return goodsSkuMapper.getBySku(skuList);
    }

    @Override
    public Integer updateById(GoodsSku sku) {
        return goodsSkuMapper.updateByPrimaryKeySelective(sku);
    }
}
