package com.lww.design.graduation.entity.vo.shiro;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShiroPermissionVO implements Serializable {
    private String sn;

    private String name;

    private String remark;

}
