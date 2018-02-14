package com.lww.design.graduation.service;


import com.lww.design.graduation.entity.po.TestA;

import java.util.List;

public interface TestAService {
    List<TestA> search();

    int insert(TestA entity);


}
