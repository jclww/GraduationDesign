package com.lww.design.graduation.service.goods.spu;

import com.lww.design.graduation.entity.po.GoodsSpu;
import com.lww.design.graduation.entity.vo.GoodsSearchRequestVO;
import com.lww.design.graduation.entity.vo.goods.GoodsDetailVO;

import java.util.List;

public interface GoodsSPUService {
    GoodsSpu getById(Long spu);

    List<GoodsSpu> getByIdList(List<Long> spuList);

    List<GoodsDetailVO> searchByKeyWord(GoodsSearchRequestVO requestVO);
}
