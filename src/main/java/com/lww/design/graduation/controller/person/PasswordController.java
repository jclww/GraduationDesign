package com.lww.design.graduation.controller.person;

import com.alibaba.fastjson.JSON;
import com.lww.design.graduation.entity.po.Address;
import com.lww.design.graduation.entity.po.user.User;
import com.lww.design.graduation.entity.vo.shiro.ShiroUserVO;
import com.lww.design.graduation.service.user.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping(value="person")
public class PasswordController {
    @Resource
    private UserInfoService userInfoService;
    @RequestMapping(value="password",method= RequestMethod.GET)
    public ModelAndView passwordIndex(ModelMap model, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
        model.addAttribute("user", shiroUserVO);
        return new ModelAndView("person/password", model);
    }

    @RequestMapping(value="password", method= RequestMethod.POST)
    @ResponseBody
    public String passwordModify(String oldPsw, String newPsw1, String newPsw2) {
        log.info("passwordModify oldPsw:{} newPsw1:{} newPsw2:{} ", oldPsw, newPsw1, newPsw2);
        if (StringUtils.isBlank(newPsw1) || StringUtils.isBlank(newPsw1)) {
            return "请输入密码";
        }
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
        if (!newPsw1.equals(newPsw2)) {
            return "密码不一致";
        }
        User user = new User();
        user.setId(shiroUserVO.getId());
        user.setPassWord(newPsw1);
        int num = userInfoService.modifyPassword(user);
        if (num == 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }
}
