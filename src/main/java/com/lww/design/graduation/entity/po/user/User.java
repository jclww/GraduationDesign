package com.lww.design.graduation.entity.po.user;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class User {
    private Long id;

    private Long account;

    private String name;

    private String passWord;

    private BigDecimal money;

    private String aliasName;

    private String email;

    private String phone;

    private Integer status;

    private Date lastLoginAt;

    private String avatarUrl;

    private Date createdAt;

    private Date modifiedAt;

    private Date deletedAt;
}