package com.lww.design.graduation.entity.vo.shiro;


import lombok.Data;

import java.util.List;

@Data
public class ShiroUserVO{
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long account;

    private String name;

    private String passWord;

    private String aliasName;

    private String email;

    private String phone;

    private String avatarUrl;
    private List<ShiroRoleVO> roleVOList;
}
