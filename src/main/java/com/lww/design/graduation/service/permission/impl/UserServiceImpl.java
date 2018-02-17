package com.lww.design.graduation.service.permission.impl;

import com.lww.design.graduation.entity.po.permission.User;
import com.lww.design.graduation.entity.vo.permission.UserPermissionVO;
import com.lww.design.graduation.service.permission.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getById(String username) {
        return null;
    }

    @Override
    public UserPermissionVO getUserPermissionById(String userName) {
        return null;
    }
}
