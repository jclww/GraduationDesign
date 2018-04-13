package com.lww.design.graduation.controller.person;

import com.alibaba.fastjson.JSON;
import com.lww.design.graduation.common.exception.BizException;
import com.lww.design.graduation.entity.po.Address;
import com.lww.design.graduation.entity.vo.address.AddressVO;
import com.lww.design.graduation.entity.vo.shiro.ShiroUserVO;
import com.lww.design.graduation.service.address.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value="person")
public class AddressController {

    @Resource
    private AddressService addressService;

    @RequestMapping(value="address",method= RequestMethod.GET)
    public ModelAndView addressIndex(ModelMap model) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
        List<Address> addressList = addressService.getAddressByUserId(shiroUserVO.getId());
        model.addAttribute("user", shiroUserVO);
        model.addAttribute("addressList", addressList);
        return new ModelAndView("person/address", model);
    }

    @RequestMapping(value="address", method= RequestMethod.POST)
    public ModelAndView addressUpset(Address address, ModelMap model) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
        address.setUserId(shiroUserVO.getId());
        addressService.upsetAddress(address);
        return new ModelAndView("redirect:/person/address", model);
    }

    @RequestMapping(value="address", method= RequestMethod.PUT)
    @ResponseBody
    public String addressModify(@RequestBody Address address) {
        log.info("addressDelete address:{}", JSON.toJSONString(address));
        Subject subject = SecurityUtils.getSubject();
        ShiroUserVO shiroUserVO = (ShiroUserVO)subject.getPrincipal();
        log.info("shiroUserVO:{}", JSON.toJSONString(shiroUserVO));
        int num = addressService.modifyAddress(address, shiroUserVO.getId());
        if (num == 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    @RequestMapping(value="address", method= RequestMethod.DELETE)
    @ResponseBody
    public String addressDelete(@RequestBody Address address) {
        log.info("addressDelete addressId:{}", address.getId());
        int num = addressService.deleteAddress(address.getId());
        if (num == 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
}
