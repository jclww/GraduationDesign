package com.lww.design.graduation.service.category;

import com.lww.design.graduation.entity.po.Category;
import com.lww.design.graduation.entity.vo.CategoryVO;

import java.util.List;

public interface CategoryService {
    List<CategoryVO> getAllCategory();

    List<Category> getLowCategory(Integer categoryId);

    List<Category> getSubCategory(Integer categoryId);

    List<Category> getRootCategory();
}
