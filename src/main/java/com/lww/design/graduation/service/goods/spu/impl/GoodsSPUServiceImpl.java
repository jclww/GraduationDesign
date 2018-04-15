package com.lww.design.graduation.service.goods.spu.impl;

import com.lww.design.graduation.entity.po.GoodsSpu;
import com.lww.design.graduation.mapper.GoodsSpuMapper;
import com.lww.design.graduation.service.goods.spu.GoodsSPUService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class GoodsSPUServiceImpl implements GoodsSPUService {
    @Resource
    private GoodsSpuMapper goodsSpuMapper;
    @Override
    public GoodsSpu getById(Long spu) {
        return goodsSpuMapper.selectByPrimaryKey(spu);
    }

    @Override
    public List<GoodsSpu> getByIdList(List<Long> spuList) {
        return goodsSpuMapper.getByIdList(spuList);
    }
}
