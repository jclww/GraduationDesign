package com.lww.design.graduation.service.goods.spu.impl;

import com.alibaba.fastjson.JSON;
import com.lww.design.graduation.common.exception.BizException;
import com.lww.design.graduation.entity.dao.goods.GoodsSO;
import com.lww.design.graduation.entity.po.Category;
import com.lww.design.graduation.entity.po.GoodsImg;
import com.lww.design.graduation.entity.po.GoodsSpu;
import com.lww.design.graduation.entity.vo.GoodsSearchRequestVO;
import com.lww.design.graduation.entity.vo.goods.GoodsDetailVO;
import com.lww.design.graduation.mapper.GoodsSpuMapper;
import com.lww.design.graduation.service.category.CategoryService;
import com.lww.design.graduation.service.goods.img.GoodsImgService;
import com.lww.design.graduation.service.goods.spu.GoodsSPUService;
import com.lww.design.graduation.utils.AppUtil;
import com.lww.design.graduation.utils.OrikaBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoodsSPUServiceImpl implements GoodsSPUService {
    @Resource
    private GoodsSpuMapper goodsSpuMapper;
    @Resource
    private CategoryService categoryService;
    @Resource
    private OrikaBeanUtil orikaBeanUtil;
    @Resource
    private GoodsImgService goodsImgService;

    @Override
    public GoodsSpu getById(Long spu) {
        return goodsSpuMapper.selectByPrimaryKey(spu);
    }

    @Override
    public List<GoodsSpu> getByIdList(List<Long> spuList) {
        return goodsSpuMapper.getByIdList(spuList);
    }

    @Override
    public List<GoodsDetailVO> searchByKeyWord(GoodsSearchRequestVO requestVO) {
        log.info("requestVO:{}", JSON.toJSONString(requestVO));
        GoodsSO goodsSO = orikaBeanUtil.convert(requestVO, GoodsSO.class);
        List<GoodsSpu> goodsSpuList = new ArrayList<>();
        if (AppUtil.nullOrDefault(requestVO.getCategoryId())) {
            // 查询所有
            goodsSO.setCategoryId(null);
            goodsSpuList = goodsSpuMapper.search(goodsSO);
        } else {
            List<Category> categoryList = categoryService.getLowCategory(requestVO.getCategoryId());
            List<Integer> categoryIdList = categoryList.stream().map(Category::getId).collect(Collectors.toList());
            goodsSO.setCategoryId(null);
            goodsSO.setCategoryIdList(categoryIdList);
            goodsSpuList = goodsSpuMapper.search(goodsSO);
        }
        if (CollectionUtils.isEmpty(goodsSpuList)) {
            return new ArrayList<>();
        }
        List<Long> spuId = goodsSpuList.stream().map(GoodsSpu::getId).collect(Collectors.toList());
        List<GoodsImg> goodsImgList = goodsImgService.getBySpuIdList(spuId);
        List<GoodsDetailVO> goodsDetailVOList = goodsSpuList.stream().map(goodsSpu -> {
            Optional<GoodsImg> img = goodsImgList.stream().filter(goodsImg -> goodsImg.getSpuId().equals(goodsSpu.getId())).findFirst();
            if (!img.isPresent()) {
                log.error("图片找不到 goodsSpu:{}", JSON.toJSONString(goodsSpu));
                throw new BizException("系统异常");
            }
            GoodsDetailVO goodsDetailVO = new GoodsDetailVO();
            goodsDetailVO.setImgUrl(img.get().getMidImgUrl());
            goodsDetailVO.setPrice(goodsSpu.getPriceBottom());
            goodsDetailVO.setSpuName(goodsSpu.getName());
            goodsDetailVO.setSpuId(goodsSpu.getId());
            return goodsDetailVO;
        }).collect(Collectors.toList());
        return goodsDetailVOList;
    }
}
