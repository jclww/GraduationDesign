package com.lww.design.graduation.controller.order;

import com.alibaba.fastjson.JSON;
import com.lww.design.graduation.common.exception.BizException;
import com.lww.design.graduation.entity.po.Cart;
import com.lww.design.graduation.entity.po.SubOrder;
import com.lww.design.graduation.entity.vo.shiro.ShiroUserVO;
import com.lww.design.graduation.service.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class OrderController {
    @Resource
    private OrderService orderService;

//    @RequestMapping(value="/order",method= RequestMethod.GET)
//    public ModelAndView createOrder(String param, ModelMap model) {
//        List<SubOrder> subOrders = (List<SubOrder>) JSON.parse(param);
//        Subject subject = SecurityUtils.getSubject();
//        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
//        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
//        if (shiroUserVO == null) {
//            return new ModelAndView("redirect:/login");
//        }
//        return new ModelAndView("redirect:/cart");
//    }
    @RequestMapping(value="/order",method= RequestMethod.POST)
    public String createOrder(@RequestParam(value = "sku[]") List<String> skus, Long addressId, Boolean shopCart, ModelMap model) {
        if (CollectionUtils.isEmpty(skus)) {
            throw new BizException("商品为空");
        }
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
        if (shiroUserVO == null) {
            return "请登录";
        }
        List<SubOrder> subOrders = skus.stream().map(s -> {
            String[] strings = s.split(":");
            SubOrder subOrder = new SubOrder();
            subOrder.setSkuId(Long.valueOf(strings[0]));
            subOrder.setCount(Integer.valueOf(strings[1]));
            return subOrder;
        }).collect(Collectors.toList());
        Long orderId = orderService.createOrder(subOrders, addressId, shiroUserVO.getId(), shopCart);
        return orderId.toString();
    }
}
