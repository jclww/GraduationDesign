package com.lww.design.graduation.entity.dao.goods;

import com.lww.design.graduation.entity.dao.base.SearchObject;
import lombok.Data;

import java.util.List;

@Data
public class GoodsSO extends SearchObject {

    private String name;

    private Integer categoryId;

    private List<Integer> categoryIdList;
}
