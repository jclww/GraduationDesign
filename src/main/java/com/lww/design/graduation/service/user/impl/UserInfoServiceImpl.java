package com.lww.design.graduation.service.user.impl;

import com.alibaba.fastjson.JSON;
import com.lww.design.graduation.entity.po.user.User;
import com.lww.design.graduation.mapper.UserMapper;
import com.lww.design.graduation.service.user.UserInfoService;
import com.lww.design.graduation.utils.EncryUtils;
import com.lww.design.graduation.utils.OrikaBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private OrikaBeanUtil orikaBeanUtil;


    @Override
    public int updateUserInfoByAccount(User record) {
        log.info("record:{}", JSON.toJSONString(record));
        return userMapper.updateByAccountSelective(record);
    }

    @Override
    public int modifyPassword(User user) {
        user.setPassWord(EncryUtils.encryPwd(user.getPassWord()));
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
