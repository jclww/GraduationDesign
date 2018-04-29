package com.lww.design.graduation.controller.goods;

import com.alibaba.fastjson.JSON;
import com.lww.design.graduation.entity.po.Category;
import com.lww.design.graduation.entity.vo.GoodsSearchRequestVO;
import com.lww.design.graduation.entity.vo.goods.GoodsDetailVO;
import com.lww.design.graduation.entity.vo.shiro.ShiroUserVO;
import com.lww.design.graduation.service.category.CategoryService;
import com.lww.design.graduation.service.goods.spu.GoodsSPUService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class SearchController {

    @Resource
    private GoodsSPUService goodsSPUService;
    @Resource
    private CategoryService categoryService;
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List searchGoodsInfo(GoodsSearchRequestVO requestVO) {
        List<GoodsDetailVO> goodsDetailVOS = goodsSPUService.searchByKeyWord(requestVO);
        return goodsDetailVOS;
    }

    @RequestMapping(value = "/redirectSearch", method = RequestMethod.GET)
    public ModelAndView redirectSearch(ModelMap model, String name) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO) subject.getPrincipal();
        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
        if (shiroUserVO != null) {
            model.addAttribute("user", shiroUserVO);
        }
        List<Category> categoryList = categoryService.getRootCategory();
        model.addAttribute("name", name);
        model.addAttribute("categoryList", categoryList);
        return new ModelAndView("search", model);
    }
    @RequestMapping(value = "/getSubCategory", method = RequestMethod.GET)
    public List getSubCategory(Integer categoryId) {
        return categoryService.getSubCategory(categoryId);
    }
}
