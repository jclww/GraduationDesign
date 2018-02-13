package com.lww.design.graduation.service.impl;

import com.lww.design.graduation.entity.TestA;
import com.lww.design.graduation.mapper.TestAMapper;
import com.lww.design.graduation.service.TestAService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestAServiceImpl implements TestAService {
    @Resource
    private TestAMapper testAMapper;

    @Override
    public List<TestA> search() {
        return testAMapper.search();
    }
}
