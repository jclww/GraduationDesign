package com.lww.design.graduation.service.goods.detail;

import com.lww.design.graduation.entity.po.GoodsDetail;

import java.util.List;

public interface GoodsDetailService {

    List<GoodsDetail> getBySpuId(Long spu);
}
