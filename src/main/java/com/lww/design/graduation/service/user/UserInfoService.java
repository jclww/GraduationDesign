package com.lww.design.graduation.service.user;

import com.lww.design.graduation.entity.po.user.User;

public interface UserInfoService {
    int updateUserInfoByAccount(User record);

//    int modifyPassword(String oldPsw, String newPsw);

    int modifyPassword(User user);
}
