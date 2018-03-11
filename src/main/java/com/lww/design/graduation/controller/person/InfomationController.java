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

public class InfomationController {
    @RequestMapping(value="information",method= RequestMethod.GET)
    public ModelAndView personIndex(ModelMap model, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        model.addAttribute("user", shiroUserVO);
        return new ModelAndView("person/information", model);
    }

}