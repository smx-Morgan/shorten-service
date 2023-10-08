package com.shorten.controller;

import com.shorten.common.ResponseResult;
import com.shorten.common.ResultUtils;
import com.shorten.dao.TestDao;
import com.shorten.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestDao testDao;

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/dao-test")
    public Test daoTest() {
        return testDao.save(Test.builder().name("abc").build());
    }

    @GetMapping("/test-restful")
    public ResponseResult<String> testRestful() {
        return ResultUtils.success("ok");
    }
}
