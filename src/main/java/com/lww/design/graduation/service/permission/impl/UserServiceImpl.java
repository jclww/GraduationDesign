package com.lww.design.graduation.service.permission.impl;

import com.alibaba.fastjson.JSON;
import com.lww.design.graduation.entity.po.user.User;
import com.lww.design.graduation.entity.vo.shiro.ShiroUserVO;
import com.lww.design.graduation.mapper.UserMapper;
import com.lww.design.graduation.service.permission.UserService;
import com.lww.design.graduation.utils.AppUtil;
import com.lww.design.graduation.utils.OrikaBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private OrikaBeanUtil orikaBeanUtil;

    @Override
    public User getById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
    @Override
    public ShiroUserVO getByAccount(Long account) {
        if (AppUtil.nullOrDefault(account)) {
            return null;
        }
        User user = userMapper.selectByAccountId(account);
        log.info("getByAccount account:{} get user:{}", account, JSON.toJSONString(user));
        return orikaBeanUtil.convert(user, ShiroUserVO.class);
    }

    @Override
    public ShiroUserVO getUserPermissionById(String userName) {
        return null;
    }

    @Override
    public Integer updateById(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
