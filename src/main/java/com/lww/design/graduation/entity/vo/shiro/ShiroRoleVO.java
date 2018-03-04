package com.lww.design.graduation.entity.vo.shiro;

import lombok.Data;

import java.util.List;

@Data
public class ShiroRoleVO {
    private String sn;

    private String name;

    private List<ShiroPermissionVO> permissionVOList;
}
