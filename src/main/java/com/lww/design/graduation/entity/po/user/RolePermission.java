package com.lww.design.graduation.entity.po.user;

import lombok.Data;

import java.util.Date;

@Data
public class RolePermission {
    private Integer id;

    private Integer roleId;

    private Integer permissionId;

    private Integer status;

    private Date createdAt;

    private Date updatedAt;

}