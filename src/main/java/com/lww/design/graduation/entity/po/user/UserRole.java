package com.lww.design.graduation.entity.po.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserRole {
    private Integer id;

    private Long userId;

    private Integer roleId;

    private Integer status;

    private Date createdAt;

    private Date updatedAt;

}