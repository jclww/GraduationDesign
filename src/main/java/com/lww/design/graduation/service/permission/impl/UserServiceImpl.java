package com.lww.design.graduation.service.permission.impl;

import com.lww.design.graduation.entity.po.permission.User;
import com.lww.design.graduation.entity.vo.permission.PermissionVO;
import com.lww.design.graduation.service.permission.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getById(String username) {
        return null;
    }

    @Override
    public PermissionVO getUserPermissionById(String userName) {
        return null;
    }
}
