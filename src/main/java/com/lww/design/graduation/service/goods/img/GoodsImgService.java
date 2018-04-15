package com.lww.design.graduation.service.goods.img;

import com.lww.design.graduation.entity.po.GoodsImg;

import java.util.List;

public interface GoodsImgService {
    List<GoodsImg> getBySpuId(Long spu);

    List<GoodsImg> getBySpuIdList(List<Long> spuList);
}
