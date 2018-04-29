package com.lww.design.graduation.controller.person;

import com.lww.design.graduation.entity.po.user.User;
import com.lww.design.graduation.entity.vo.shiro.ShiroUserVO;
import com.lww.design.graduation.service.permission.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class PersonController {
    @Resource
    private UserService userService;

    @RequestMapping(value="person",method= RequestMethod.GET)
    public ModelAndView personIndex(ModelMap model, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("login user:{}", shiroUserVO);
        User user = userService.getById(shiroUserVO.getId());
        model.addAttribute("user", user);
        return new ModelAndView("person/index", model);
    }
    @RequestMapping(value="person/logout",method= RequestMethod.GET)
    public ModelAndView personLogout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ModelAndView("redirect:/html/login.html");
    }
}
