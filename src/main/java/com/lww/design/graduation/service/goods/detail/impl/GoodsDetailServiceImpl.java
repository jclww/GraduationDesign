package com.lww.design.graduation.service.goods.detail.impl;

import com.lww.design.graduation.entity.po.GoodsDetail;
import com.lww.design.graduation.mapper.GoodsDetailMapper;
import com.lww.design.graduation.service.goods.detail.GoodsDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class GoodsDetailServiceImpl implements GoodsDetailService {
    @Resource
    private GoodsDetailMapper goodsDetailMapper;
    @Override
    public List<GoodsDetail> getBySpuId(Long spu) {
        return goodsDetailMapper.getBySpuId(spu);
    }
}
