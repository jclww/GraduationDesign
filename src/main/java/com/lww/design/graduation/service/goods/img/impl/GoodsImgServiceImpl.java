package com.lww.design.graduation.service.goods.img.impl;

import com.lww.design.graduation.entity.po.GoodsImg;
import com.lww.design.graduation.mapper.GoodsImgMapper;
import com.lww.design.graduation.service.goods.img.GoodsImgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class GoodsImgServiceImpl implements GoodsImgService {
    @Resource
    private GoodsImgMapper goodsImgMapper;
    @Override
    public List<GoodsImg> getBySpuId(Long spu) {
        return goodsImgMapper.getBySpuId(spu);
    }
}
