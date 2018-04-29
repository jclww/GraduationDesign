package com.lww.design.graduation.controller.admin;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
@RequestMapping(value="/admin")
public class GoodsManagerController {

    @RequestMapping(value="goods",method= RequestMethod.GET)
    public ModelAndView addressIndex(ModelMap model) {
        return new ModelAndView("admin/goods", model);
    }
}
