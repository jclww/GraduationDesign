package com.lww.design.graduation.controller.person;

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
public class PersonController {
    @RequestMapping(value="person",method= RequestMethod.GET)
    public ModelAndView personIndex(ModelMap model, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("login user:{}", shiroUserVO);
        model.addAttribute("userName", shiroUserVO.getName());
        model.addAttribute("avatar", shiroUserVO.getAvatarUrl());
        model.addAttribute("nickName", shiroUserVO.getAliasName());
        return new ModelAndView("person/index", model);
    }

}
