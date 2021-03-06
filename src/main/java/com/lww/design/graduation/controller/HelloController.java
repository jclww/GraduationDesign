package com.lww.design.graduation.controller;

import com.alibaba.fastjson.JSON;
import com.lww.design.graduation.entity.po.Category;
import com.lww.design.graduation.entity.vo.CategoryVO;
import com.lww.design.graduation.service.category.CategoryService;
import com.lww.design.graduation.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author Lww
 * @date 2017/12/11
 */
@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
//
//    @Resource
//    private TestAService testAService;
    @Resource
    private RedisUtil redisUtil;

    @Resource
    private CategoryService categoryService;
    @RequestMapping("/category")
    @ResponseBody
    public List<CategoryVO> categoryService() {
        return categoryService.getAllCategory();
    }
    @RequestMapping("/hello")
    @ResponseBody
    public String selectByName(ModelMap model, HttpServletRequest hsr) {
        String str = "sadsa";
        return JSON.toJSONString(str);
    }

    @RequestMapping("/search1")
    @ResponseBody
    public String search() {
        logger.info("asdsadsa");
//        List<TestA> aList = testAService.search();
//        String aStr = JSON.toJSONString(aList);
//        logger.info(aStr);
//        return aStr;
        return "asdsadsa";
    }

    @RequestMapping("/error")
    @ResponseBody
    public String error() {
        logger.error("error message!");
        return "error";
    }

    @RequestMapping("/insert")
    @ResponseBody
    public String insert() {
//        TestA a = new TestA();
//        a.setName("test1");
//        a.setNum(111);
//        a.setPhone("18729443");
//        a.setSex("男");
//        try {
//            testAService.insert(a);
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            return "error";
//        }
        return "success";
    }
    @RequestMapping("/redis")
    @ResponseBody
    public String redis() {
        redisUtil.setString("name","tom");
        String name = redisUtil.getString("name");
        return name;
    }

}
