package com.lww.design.graduation.entity.po.permission;

import lombok.*;
import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;

    private String passWord;


}
