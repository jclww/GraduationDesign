package com.lww.design.graduation.service.test;


import com.lww.design.graduation.entity.po.TestA;

import java.util.List;

public interface TestAService {
    List<TestA> search();

    int insert(TestA entity);


}