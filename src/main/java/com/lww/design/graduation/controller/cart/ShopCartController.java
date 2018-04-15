package com.lww.design.graduation.controller.cart;

import com.alibaba.fastjson.JSON;
import com.lww.design.graduation.entity.po.Cart;
import com.lww.design.graduation.entity.po.GoodsDetail;
import com.lww.design.graduation.entity.vo.goods.GoodsDetailVO;
import com.lww.design.graduation.entity.vo.shiro.ShiroUserVO;
import com.lww.design.graduation.service.cart.ShopCartService;
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
public class ShopCartController {
    @Resource
    private ShopCartService shopCartService;

    @RequestMapping(value="/cart", method= RequestMethod.POST)
    public ModelAndView addGoods(Cart cart) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
        if (shiroUserVO == null) {
            return new ModelAndView("redirect:/login");
        }
        int addStatus = shopCartService.addGoods(cart, shiroUserVO.getId());
        return new ModelAndView("redirect:/cart");
    }

    @RequestMapping(value="/cart", method= RequestMethod.GET)
    public ModelAndView cartDetail(ModelMap model) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
        if (shiroUserVO == null) {
            return new ModelAndView("redirect:/login");
        }
        model.addAttribute("user", shiroUserVO);
        List<GoodsDetailVO> goodsDetailVOList = shopCartService.getAllGoods(shiroUserVO.getId());
        model.addAttribute("goodsList", goodsDetailVOList);
        return new ModelAndView("shopcart", model);
    }
    @RequestMapping(value="/cart/{cartId}",method= RequestMethod.DELETE)
    public String deleteCartById(@PathVariable(value = "cartId") Long cartId) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
        if (shiroUserVO == null) {
            return "请登录";
        }
        int deleteSuccess = shopCartService.deleteCartById(cartId);
        return "success";
    }

}
