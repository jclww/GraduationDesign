package com.lww.design.graduation.controller.login;

import com.lww.design.graduation.entity.po.user.User;
import com.lww.design.graduation.service.permission.UserService;
import com.lww.design.graduation.utils.EncryUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class RegisterController {
    @Resource
    private UserService userService;

    @RequestMapping(value="/registerByEmail",method= RequestMethod.POST)
    @ResponseBody
    public String registerByEmail(String email, String passWord, String passWordRepeat) {
        if (!email.contains("@")) {
            return "请输入正确邮箱";
        }
        if (StringUtils.isBlank(passWord) || StringUtils.isBlank(passWordRepeat)) {
            return "请输入密码";
        }
        if (!passWord.equals(passWordRepeat)) {
            return "密码不一致";
        }
        User user = new User();
        user.setEmail(email);
        user.setPassWord(EncryUtils.encryPwd(passWord));
        int status = userService.insert(user);
        return "注册成功";
    }

    @RequestMapping(value="/registerByPhone",method= RequestMethod.POST)
    @ResponseBody
    public String registerByPhone(String phone, String passWord, String passWordRepeat) {
        if (!(phone.length() == 11)) {
            return "请输入正确手机号";
        }
        if (StringUtils.isBlank(passWord) || StringUtils.isBlank(passWordRepeat)) {
            return "请输入密码";
        }
        if (!passWord.equals(passWordRepeat)) {
            return "密码不一致";
        }
        User user = new User();
        user.setPhone(phone);
        user.setPassWord(EncryUtils.encryPwd(passWord));
        int status = userService.insert(user);
        return "注册成功";
    }
}
