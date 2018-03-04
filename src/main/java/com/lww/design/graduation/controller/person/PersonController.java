package com.lww.design.graduation.controller.person;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class PersonController {
    @RequestMapping(value="person",method= RequestMethod.GET)
    public ModelAndView dispartcher(ModelMap model, HttpServletRequest request) {
        return new ModelAndView("person/index");
    }

}
