package com.lww.design.graduation.controller.login;

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
public class LoginController {

    @RequestMapping(value="login",method= RequestMethod.POST)
    public ModelAndView login(ModelMap model, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ModelAndView("redirect:/dispartcher");
        }
        log.info("login");
        return new ModelAndView("redirect:/login");
    }
    @RequestMapping(value="login",method= RequestMethod.GET)
    public ModelAndView redirectLogin(ModelMap model, HttpServletRequest request) {
//        return "redirect:/login.html";
        return new ModelAndView("redirect:/html/login.html");
    }

    @RequestMapping(value="dispartcher",method= RequestMethod.GET)
    public ModelAndView dispartcher(ModelMap model, HttpServletRequest request) {
        // TODO 需要判断是管理员 还是用户 现在直接是用户了
        return new ModelAndView("redirect:/person");
    }
}
