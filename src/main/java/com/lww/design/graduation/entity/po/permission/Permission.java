package com.lww.design.graduation.entity.po.permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = 2683590474689747957L;

    private Long id;
    private String name;
    private String description;
    private String permission;

    private Permission parent;

    private List<Permission> childrenList;

    private List<Role> roleList;

}
