package com.lww.design.graduation.controller.person;


import com.alibaba.fastjson.JSON;
import com.lww.design.graduation.entity.vo.OrderInfoVO;
import com.lww.design.graduation.entity.vo.shiro.ShiroUserVO;
import com.lww.design.graduation.service.order.OrderService;
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
@RequestMapping(value="/person")
public class PersonOrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping(value="/order",method= RequestMethod.GET)
    public ModelAndView orderIndex(ModelMap model) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
        model.addAttribute("user", shiroUserVO);
        List<OrderInfoVO> orderInfoVOList = orderService.getOrderInfoByUserId(shiroUserVO.getId());
        model.addAttribute("orderInfoList", orderInfoVOList);
        return new ModelAndView("/person/order", model);
    }
}
