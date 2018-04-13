package com.lww.design.graduation.controller.common;

import com.alibaba.fastjson.JSON;
import com.lww.design.graduation.entity.vo.shiro.ShiroUserVO;
import com.lww.design.graduation.service.category.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@RestController
@Slf4j
public class IndexController {

    @Resource
    private CategoryService categoryService;

    @RequestMapping(value = "/index")
    public ModelAndView index(ModelMap model) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
        if (shiroUserVO != null) {
            model.addAttribute("user", shiroUserVO);
        }
        model.addAttribute("categoryList", categoryService.getAllCategory());
        return new ModelAndView("home", model);
    }

    @RequestMapping(value="/logout",method= RequestMethod.GET)
    public ModelAndView personLogout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ModelAndView("redirect:/html/login.html");
    }
}
