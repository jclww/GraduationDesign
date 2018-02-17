package com.lww.design.graduation.service.test.impl;

import com.alibaba.fastjson.JSON;
import com.lww.design.graduation.controller.HelloController;
import com.lww.design.graduation.entity.po.TestA;
import com.lww.design.graduation.mapper.TestAMapper;
import com.lww.design.graduation.service.test.TestAService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestAServiceImpl implements TestAService {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Resource
    private TestAMapper testAMapper;

    @Override
    public List<TestA> search() {
        return testAMapper.search();
    }

    @Override
    @Transactional
    public int insert(TestA entity) {
        logger.info("insert entity:{}",JSON.toJSONString(entity));
        testAMapper.insert(entity);
//        if (1 == 1) {
//            throw new NullPointerException("asad");
//        }
        testAMapper.insert(entity);
        return 0;
    }
}
