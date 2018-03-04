package com.lww.design.graduation.entity.vo.shiro;


import lombok.Data;

import java.util.List;

@Data
public class ShiroUserVO{
    private static final long serialVersionUID = 1L;

    private String userName;

    private String passWord;

    private List<ShiroRoleVO> roleVOList;
}
