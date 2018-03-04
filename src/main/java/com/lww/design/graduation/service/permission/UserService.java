package com.lww.design.graduation.service.permission;

import com.lww.design.graduation.entity.vo.shiro.ShiroUserVO;

public interface UserService {
    ShiroUserVO getById(String username);

    ShiroUserVO getByAccount(Long account);

    ShiroUserVO getUserPermissionById(String userName);
}
