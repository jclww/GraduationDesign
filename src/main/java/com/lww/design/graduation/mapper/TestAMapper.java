package com.lww.design.graduation.mapper;

import com.lww.design.graduation.entity.TestA;

import java.util.List;

public interface TestAMapper {
    List<TestA> search();

    int insert(TestA entity);
}
