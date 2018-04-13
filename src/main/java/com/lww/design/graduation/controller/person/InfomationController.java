package com.lww.design.graduation.controller.person;

import com.alibaba.fastjson.JSON;
import com.lww.design.graduation.common.AppConstant;
import com.lww.design.graduation.entity.po.user.User;
import com.lww.design.graduation.entity.vo.shiro.ShiroUserVO;
import com.lww.design.graduation.service.user.UserInfoService;
import com.lww.design.graduation.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping(value="person")
public class InfomationController {
    @Resource
    private UserInfoService userInfoService;

    @RequestMapping(value="information",method= RequestMethod.GET)
    public ModelAndView personIndex(ModelMap model, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
        model.addAttribute("user", shiroUserVO);
        return new ModelAndView("person/information", model);
    }

    @RequestMapping(value="modify",method= RequestMethod.POST)
    @ResponseBody
    public String modify(User user) {
        log.info("modify userInfo user:{}", JSON.toJSONString(user));
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
        user.setAccount(shiroUserVO.getAccount());
        int modify = userInfoService.updateUserInfoByAccount(user);
        log.info("modify:{}", JSON.toJSONString(modify));
        return "修改成功";
    }

    @RequestMapping(value="/fileupload/avatar",method= RequestMethod.POST)
    @ResponseBody
    public String fileupload(MultipartFile uploadFile) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        String avatarPwd = AppConstant.AVATAR_UPLOAD_PATH + shiroUserVO.getAccount() + "/";
        try {
            FileUtil.saveFile(uploadFile, avatarPwd);
        } catch (Exception e) {
            log.error(e.toString());
            return "上传失败";
        }
        String getAvatarUrl = AppConstant.GET_AVATAR_PREFIX + shiroUserVO.getAccount() + "/" + uploadFile.getOriginalFilename();
        User user = new User();
        user.setAccount(shiroUserVO.getAccount());
        user.setAvatarUrl(getAvatarUrl);
        int modify = userInfoService.updateUserInfoByAccount(user);
        log.info("modify:{}", JSON.toJSONString(modify));
        return "上传成功";
    }
}