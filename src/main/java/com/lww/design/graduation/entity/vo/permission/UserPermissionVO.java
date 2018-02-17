package com.lww.design.graduation.entity.vo.permission;

import com.lww.design.graduation.entity.po.permission.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserPermissionVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;

    private String passWord;

    private List<Role> roleList;

}
