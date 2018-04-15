package com.lww.design.graduation.service.goods.spu;

import com.lww.design.graduation.entity.po.GoodsSpu;

import java.util.List;

public interface GoodsSPUService {
    GoodsSpu getById(Long spu);

    List<GoodsSpu> getByIdList(List<Long> spuList);
}
