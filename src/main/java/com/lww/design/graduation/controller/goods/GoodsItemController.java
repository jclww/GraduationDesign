package com.lww.design.graduation.controller.goods;

import com.alibaba.fastjson.JSON;
import com.lww.design.graduation.entity.po.GoodsDetail;
import com.lww.design.graduation.entity.po.GoodsImg;
import com.lww.design.graduation.entity.po.GoodsSpu;
import com.lww.design.graduation.entity.vo.goods.GoodsAttributeVO;
import com.lww.design.graduation.entity.vo.goods.GoodsSkuInfoVO;
import com.lww.design.graduation.entity.vo.shiro.ShiroUserVO;
import com.lww.design.graduation.service.goods.detail.GoodsDetailService;
import com.lww.design.graduation.service.goods.img.GoodsImgService;
import com.lww.design.graduation.service.goods.option.GoodsOptionService;
import com.lww.design.graduation.service.goods.sku.GoodsSKUService;
import com.lww.design.graduation.service.goods.spu.GoodsSPUService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class GoodsItemController {
    @Resource
    private GoodsSPUService goodsSPUService;
    @Resource
    private GoodsImgService goodsImgService;
    @Resource
    private GoodsDetailService goodsDetailService;
    @Resource
    private GoodsOptionService goodsOptionService;
    @Resource
    private GoodsSKUService goodsSKUService;

    @RequestMapping(value="/item/{spu}",method= RequestMethod.GET)
    public ModelAndView getGoodsInfo(@PathVariable(value = "spu") Long spu, ModelMap model) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
        if (shiroUserVO != null) {
            model.addAttribute("user", shiroUserVO);
        }
        GoodsSpu goodsSpu = goodsSPUService.getById(spu);
        model.addAttribute("spu", goodsSpu);
        List<GoodsImg> goodsImgList = goodsImgService.getBySpuId(spu);
        model.addAttribute("imgs", goodsImgList);
        List<GoodsDetail> goodsDetailList = goodsDetailService.getBySpuId(spu);
        model.addAttribute("details", goodsDetailList);
        List<GoodsAttributeVO> attributeVOS = goodsOptionService.getGoodsAttributeBySpuId(spu);
        model.addAttribute("attributes", attributeVOS);
        List<GoodsSkuInfoVO> skuInfoVOS = goodsSKUService.getGoodsSkuInfoBySpuId(spu);
        model.addAttribute("skuInfos", JSON.toJSONString(skuInfoVOS));
        return new ModelAndView("introduction", model);
    }
}
