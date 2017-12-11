package com.lww.design.graduation.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Lww
 * @date 2017/12/11
 */
@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @RequestMapping("/selectByName")
    @ResponseBody
    public String selectByName(ModelMap model, HttpServletRequest hsr) {
        logger.info("asdasdas");
        String str = "sadsa";
        return JSON.toJSONString(str);
    }
}
