package com.lww.design.graduation.service.category.impl;

import com.lww.design.graduation.entity.po.Category;
import com.lww.design.graduation.entity.vo.CategoryVO;
import com.lww.design.graduation.mapper.CategoryMapper;
import com.lww.design.graduation.service.category.CategoryService;
import com.lww.design.graduation.utils.OrikaBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private OrikaBeanUtil orikaBeanUtil;

    @Override
    public List<CategoryVO> getAllCategory() {
        log.info("getAllCategory");
        // 查出一级分类
        List<Category> rootCategory = categoryMapper.getRoot();
        List<Integer> categortIdList = rootCategory.stream().map(Category::getId).collect(Collectors.toList());
        // 查出二级分类
        List<Category> secondCategory = categoryMapper.queryByParentId(categortIdList);
        categortIdList = secondCategory.stream().map(Category::getId).collect(Collectors.toList());
        // 查出三级分类
        List<Category> thirdCategory = categoryMapper.queryByParentId(categortIdList);

        List<CategoryVO> rootCategoryVOList =  orikaBeanUtil.convertList(rootCategory, CategoryVO.class);
        List<CategoryVO> secondCategoryVOList =  orikaBeanUtil.convertList(secondCategory, CategoryVO.class);
        List<CategoryVO> thirdCategoryVOList =  orikaBeanUtil.convertList(thirdCategory, CategoryVO.class);
        // 填充二级分类的子分类信息
        secondCategoryVOList.forEach(categoryVO -> {
            List<CategoryVO> children = thirdCategoryVOList.stream()
                    .filter(child -> categoryVO.getId().equals(child.getParentId()))
                    .collect(Collectors.toList());
            categoryVO.setChildren(children);
        });
        // 填充一级分类的子分类信息
        rootCategoryVOList.forEach(categoryVO -> {
            List<CategoryVO> children = secondCategoryVOList.stream()
                    .filter(i -> categoryVO.getId().equals(i.getParentId()))
                    .collect(Collectors.toList());
            categoryVO.setChildren(children);
        });
        return rootCategoryVOList;
    }
}
