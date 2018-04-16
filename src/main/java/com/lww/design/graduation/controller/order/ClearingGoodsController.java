package com.lww.design.graduation.controller.order;

import com.alibaba.fastjson.JSON;
import com.lww.design.graduation.entity.po.Address;
import com.lww.design.graduation.entity.po.Cart;
import com.lww.design.graduation.entity.po.ClearingGoods;
import com.lww.design.graduation.entity.po.SubOrder;
import com.lww.design.graduation.entity.vo.goods.GoodsDetailVO;
import com.lww.design.graduation.entity.vo.shiro.ShiroUserVO;
import com.lww.design.graduation.service.address.AddressService;
import com.lww.design.graduation.service.order.ClearingGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class ClearingGoodsController {

    @Resource
    private AddressService addressService;
    @Resource
    private ClearingGoodsService clearingGoodsService;

    @RequestMapping(value="/buy",method= RequestMethod.POST)
    public String createOrder(@RequestBody List<Cart> cartList, ModelMap model) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
        if (shiroUserVO == null) {
            return "/login";
        }
//        List<Address> addressList = addressService.getAddressByUserId(shiroUserVO.getId());
//        model.addAttribute("user", shiroUserVO);
//        model.addAttribute("addressList", addressList);
        String json = JSON.toJSONString(cartList);
        return "/pay?param="+json;
    }

    @RequestMapping(value="/buy",method= RequestMethod.GET)
    public ModelAndView createOrder(String param, ModelMap model) {
        List<ClearingGoods> clearingGoodsList = JSON.parseArray(param, ClearingGoods.class);
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
        if (shiroUserVO == null) {
            return new ModelAndView("redirect:/login");
        }
        List<Address> addressList = addressService.getAddressByUserId(shiroUserVO.getId());
        model.addAttribute("user", shiroUserVO);
        model.addAttribute("addressList", addressList);

        List<GoodsDetailVO> goodsDetailVOList = clearingGoodsService.getGoodsInfo(clearingGoodsList);
        model.addAttribute("goodsList", goodsDetailVOList);
        return new ModelAndView("pay", model);
    }
}
