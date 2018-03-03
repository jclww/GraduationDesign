package com.lww.design.graduation.entity.po.user;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Integer id;

    private String sn;

    private String name;

    private Integer status;

    private String remark;

    private Date createdAt;

    private Date updatedAt;

}