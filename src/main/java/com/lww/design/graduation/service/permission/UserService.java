package com.lww.design.graduation.service.permission;

import com.lww.design.graduation.entity.po.permission.User;
import com.lww.design.graduation.entity.vo.permission.UserPermissionVO;

public interface UserService {
    User getById(String username);

    UserPermissionVO getUserPermissionById(String userName);
}
