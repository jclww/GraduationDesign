package com.lww.design.graduation.mapper;


import com.lww.design.graduation.entity.po.user.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    User selectByAccountId(Long account);

    int updateByPrimaryKeySelective(User record);

    int updateByAccountSelective(User record);

    int updateByPrimaryKey(User record);
}