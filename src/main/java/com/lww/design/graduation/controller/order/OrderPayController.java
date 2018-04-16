package com.lww.design.graduation.controller.order;

import com.alibaba.fastjson.JSON;
import com.lww.design.graduation.entity.vo.shiro.ShiroUserVO;
import com.lww.design.graduation.service.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderPayController {
    @Resource
    private OrderService orderService;


    @RequestMapping(value="/pay",method= RequestMethod.POST)
    public String orderPay(Long orderId, ModelMap model) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
        if (shiroUserVO == null) {
            return "请登录";
        }
        return orderService.payOrder(orderId, shiroUserVO.getId());
    }
}
