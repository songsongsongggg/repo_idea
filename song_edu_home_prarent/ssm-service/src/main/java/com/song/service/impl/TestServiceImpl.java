package com.song.service.impl;

import com.song.dao.TestMapper;
import com.song.domain.Test;
import com.song.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<Test> findAllTest() {
        return testMapper.findAllTest();
    }
}
