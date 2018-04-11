package com.lww.design.graduation.controller.person;

import com.alibaba.fastjson.JSON;
import com.lww.design.graduation.entity.vo.shiro.ShiroUserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping(value="person")
public class AddressController {

    @RequestMapping(value="address",method= RequestMethod.GET)
    public ModelAndView addressIndex(ModelMap model, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
        model.addAttribute("user", shiroUserVO);
        return new ModelAndView("person/address", model);
    }
}
