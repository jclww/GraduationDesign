package com.lww.design.graduation.service.permission;

import com.lww.design.graduation.entity.po.user.User;
import com.lww.design.graduation.entity.vo.shiro.ShiroUserVO;

public interface UserService {
    User getById(Long userId);
    ShiroUserVO getByAccount(Long account);

    ShiroUserVO getUserPermissionById(String userName);

    Integer updateById(User user);
}
