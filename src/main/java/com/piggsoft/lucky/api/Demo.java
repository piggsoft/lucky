package com.piggsoft.lucky.api;

import com.piggsoft.lucky.config.Api;
import com.piggsoft.lucky.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * <br>Created by fire pigg on 2016/01/19.
 *
 * @author piggsoft@163.com
 */
public class Demo {


    @Autowired
    private UserMapper userMapper;

    @Api("haha")
    public String helloworld(String json) {
        System.out.println(userMapper);
        return "hello world";
    }
}
