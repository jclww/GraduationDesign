package com.lww.design.graduation.entity.po.permission;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = 6177417435897400228L;

    private Long id;
    private String name;
    private String description;

    private List<User> userList;

    private List<Permission> permissionList;

}
