package com.lww.design.graduation.service.goods.option.impl;

import com.lww.design.graduation.entity.po.GoodsOption;
import com.lww.design.graduation.entity.po.GoodsSku;
import com.lww.design.graduation.entity.vo.goods.GoodsAttributeVO;
import com.lww.design.graduation.entity.vo.goods.GoodsOptionVO;
import com.lww.design.graduation.mapper.GoodsOptionMapper;
import com.lww.design.graduation.service.goods.option.GoodsOptionService;
import com.lww.design.graduation.service.goods.sku.GoodsSKUService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoodsOptionServiceImpl implements GoodsOptionService {
    @Resource
    private GoodsOptionMapper goodsOptionMapper;
    @Resource
    private GoodsSKUService goodsSKUService;

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
    @Override
    public List<GoodsAttributeVO> getGoodsAttributeBySpuId(Long spu) {
        List<GoodsSku> skuList = goodsSKUService.getSkuBySpu(spu);
        List<Long> skuIdList = skuList.stream().map(GoodsSku::getId).collect(Collectors.toList());
        List<GoodsOption> goodsOptions = getBySkuList(skuIdList);
        Map<Integer, List<GoodsOption>> stringListMap = goodsOptions.stream().collect(Collectors.groupingBy(GoodsOption::getAttributeId));
        List<GoodsAttributeVO> attributeVOList = stringListMap.entrySet().stream().map(entry -> {
            GoodsAttributeVO attributeVO = new GoodsAttributeVO();
            attributeVO.setAttributeId(entry.getKey());
            attributeVO.setAttributeName(entry.getValue().get(0).getAttributeName());
            List<GoodsOptionVO> optionVOS = entry.getValue().stream().filter(distinctByKey(GoodsOption::getOptionId)).map(option -> {
                GoodsOptionVO optionVO = new GoodsOptionVO();
                optionVO.setId(entry.getKey()+":"+option.getOptionId());
                optionVO.setName(option.getOptionName());
                return optionVO;
            }).collect(Collectors.toList());
            attributeVO.setOptionList(optionVOS);
            return attributeVO;
        }).sorted(Comparator.comparing(GoodsAttributeVO::getAttributeId))
                .collect(Collectors.toList());
        return attributeVOList;
    }

    @Override
    public List<GoodsOption> getBySkuList(List<Long> skuIdList) {
        return goodsOptionMapper.getBySkuList(skuIdList);
    }
}
